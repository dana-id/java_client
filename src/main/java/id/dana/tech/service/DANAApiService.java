/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import id.dana.tech.model.client.ApiAction;
import id.dana.tech.model.client.DANAApiConfig;
import id.dana.tech.model.client.enums.ApiType;
import id.dana.tech.service.client.ApiErrorHandler;
import id.dana.tech.service.client.HttpClient;
import id.dana.tech.util.CommonUtil;
import id.dana.tech.util.LogUtil;
import id.dana.tech.util.annotation.API;
import id.dana.tech.util.exception.DANAApiInvocationException;
import id.dana.tech.util.exception.DANAApiMethodNotFoundException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANAApiService.java, v 0.1 2025‐03‐27 03.13 SLW Exp $$
 */
public class DANAApiService {

    private static final String              API_PACKAGE  = "id.dana.tech.api";

    // Map of serviceId to service instances
    private static final Map<String, Object> serviceCache = new ConcurrentHashMap<>();

    // Map of "method name" to java.lang.reflect.Method
    private static final Map<String, Method> methodCache  = new ConcurrentHashMap<>();

    private static volatile boolean          initialized  = false;

    private static void ensureInitialized() {
        if (!initialized) {
            synchronized (DANAApiService.class) {
                if (!initialized) {
                    initialize();
                    initialized = true;
                }
            }
        }
    }

    public static <REQ, RES> RES call(ApiAction<REQ, RES> apiAction, REQ request) {

        // Make sure the config is initialized, by USER or DEFAULT
        DANAApiConfig.get();

        // Lazy initialization of HTTP client, since configs depends on DANAApiConfig
        ensureInitialized();

        Method method = methodCache.get(apiAction.getServiceId());
        if (method == null) {
            throw new DANAApiMethodNotFoundException(apiAction.getServiceId());
        } else {
            if (!method.getParameterTypes()[0].isAssignableFrom(request.getClass())) {
                throw new DANAApiInvocationException("Invalid argument, expected: " + method.getParameterTypes()[0] + ", got " + request.getClass());
            }

            Object service = serviceCache.get(apiAction.getServiceId());
            if (service == null) {
                service = serviceCache.get(apiAction.getServiceId().substring(0, apiAction.getServiceId().indexOf("#")));

                if (service == null) {
                    throw new DANAApiInvocationException("Can't find HTTP client for: " + apiAction.getServiceId());
                }
            }

            try {
                Object result = method.invoke(service, request);
                if (result instanceof Call) {
                    Response<RES> response = ((Call<RES>) result).execute();

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            return apiAction.getResponseType().cast(response.body());
                        } else {
                            LogUtil.warn("[DANAApiService] Null API Response Body, please check!");
                            return apiAction.getResponseType().cast(response.body());
                        }
                    } else {
                        return ApiErrorHandler.parseErrorBody(response.errorBody(), apiAction);
                    }
                } else {
                    throw new DANAApiInvocationException("Invalid response, expected: instanceof Call" + method.getParameterTypes()[0] + ", got " + result.getClass().getName());
                }
            } catch (Exception e) {
                throw new DANAApiInvocationException(apiAction.getServiceId(), e);
            }
        }
    }

    private static void initialize() {

        Reflections reflections = new Reflections(
            new ConfigurationBuilder().forPackages(API_PACKAGE)
                .filterInputsBy(new FilterBuilder().includePackage(API_PACKAGE))
                .setScanners(new SubTypesScanner(false), new TypeAnnotationsScanner()));

        Set<Class<?>> scannedClasses = reflections.getSubTypesOf(Object.class);

        Set<Class<?>> validClass = new HashSet<>();

        Map<String, ApiType> collectedApis = new LinkedHashMap<>();

        LogUtil.info("[DANAApiService] Registering API Service...");
        for (Class<?> clazz : scannedClasses) {

            // Class-level API annotation
            if (clazz.isAnnotationPresent(API.class)) {
                validClass.add(clazz);

                API api = clazz.getAnnotation(API.class);
                collectedApis.put(clazz.getName(), api.apiType());

                for (Method method : clazz.getMethods()) {
                    scanApiService(clazz, method);
                }
            } else {
                // Method-level API annotation
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(API.class)) {
                        validClass.add(clazz);

                        API api = method.getAnnotation(API.class);
                        collectedApis.put(CommonUtil.buildServiceId(clazz, method.getName()),
                            api.apiType());

                        scanApiService(clazz, method);
                    }
                }
            }
        }

        LogUtil.info(methodCache.toString());

        LogUtil.info("[DANAApiService] Scanned {} @DANAAPI class: {}", validClass.size(),
            validClass.stream().map(Class::getSimpleName).collect(Collectors.joining(", ")));

        LogUtil.info("[DANAApiService] Registering HTTP Client...");
        for (Map.Entry<String, ApiType> api : collectedApis.entrySet()) {
            try {
                registerHttpClient(api.getKey(), api.getValue());
            } catch (ClassNotFoundException e) {
                LogUtil.error("Failed to register HTTP Client for {}, error: {}", api.getKey(), e);
            }
        }
        LogUtil.info("[DANAApiService] Ready {} HTTP Client {}", validClass.size(),
            validClass.stream().map(Class::getSimpleName).collect(Collectors.joining(", ")));
    }

    private static void scanApiService(Class<?> serviceClass, Method method) {

        Class<?> returnType = method.getReturnType();

        if (Call.class.isAssignableFrom(returnType)) {
            returnType = (Class<?>) ((java.lang.reflect.ParameterizedType) method
                .getGenericReturnType()).getActualTypeArguments()[0];
        }

        LogUtil.info("[DANAApiService]     {}#{} -> {}", serviceClass.getSimpleName(),
            method.getName(), returnType);

        String key = CommonUtil.buildServiceId(serviceClass, method.getName());
        methodCache.putIfAbsent(key, method);
    }

    private static void registerHttpClient(String serviceId,
                                           ApiType apiType) throws ClassNotFoundException {

        Class<?> serviceClass = getClass(serviceId);
        serviceCache.computeIfAbsent(serviceId, svcId -> {
            Retrofit retrofit = HttpClient.get(serviceId, apiType);
            return retrofit.create(serviceClass);
        });
    }

    private static Class<?> getClass(String serviceId) throws ClassNotFoundException {

        Class<?> serviceClass = null;
        if (serviceId.contains("#")) {
            serviceClass = Class.forName(serviceId.substring(0, serviceId.indexOf("#")));
        } else {
            serviceClass = Class.forName(serviceId);
        }

        return serviceClass;
    }
}