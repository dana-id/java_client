/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.dana.tech.model.client.DANAApiConfig;
import id.dana.tech.model.client.enums.ApiType;
import id.dana.tech.service.client.header.impl.SNAPApplyTokenHeaderProvider;
import id.dana.tech.service.client.header.impl.SNAPB2B2CHeaderProviderImpl;
import id.dana.tech.service.client.header.impl.SNAPBasicHeaderProviderImpl;
import id.dana.tech.service.client.interceptor.HeaderInterceptor;
import id.dana.tech.service.client.interceptor.LoggingInterceptor;
import id.dana.tech.util.LogUtil;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: HttpClient.java, v 0.1 2025‐03‐21 16.59 SLW Exp $$
 */
public class HttpClient {

    // Map of "service id" to "Retrofit instances"
    private static final Map<String, Retrofit> retrofitInstances = new ConcurrentHashMap<>();

    public static Retrofit get(String serviceId, ApiType apiType) {
        return retrofitInstances.computeIfAbsent(serviceId,
            inst -> getInstance(serviceId, apiType));
    }

    private static Retrofit getInstance(String serviceId, ApiType apiType) {

        LogUtil.info("[HttpClient] New HTTP Client({}) for {}", apiType.name(), serviceId);
        ObjectMapper objectMapper = new ObjectMapper();

        return new Retrofit.Builder().baseUrl(DANAApiConfig.get().getEnvironment().getUrl())
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(HttpClientProvider.getClient(apiType)).build();

        //        if (retrofit == null) {
        //            synchronized (HttpClient.class) {
        //                if (retrofit == null) {
        //                    ObjectMapper objectMapper = new ObjectMapper();
        //                    retrofit = new Retrofit.Builder()
        //                        .baseUrl(DANAApiConfig.get().getEnvironment().getUrl())
        //                        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        //                        .client(HttpClientProvider.getSNAPClient()).build();
        //                }
        //            }
        //        }
        //
        //        return retrofit;
    }

    static class HttpClientProvider {

        // Cache OkHttpClient per API identifier
        private static final Map<ApiType, OkHttpClient> httpClientMap = new ConcurrentHashMap<>();
        static {
            for (ApiType apiType : ApiType.values()) {
                httpClientMap.put(apiType, createClient(apiType));
            }
        }

        public static OkHttpClient getClient(ApiType apiType) {
            return httpClientMap.get(apiType);
        }

        private static OkHttpClient createClient(ApiType apiType) {
            DANAApiConfig config = DANAApiConfig.get();

            return new OkHttpClient.Builder()
                .connectTimeout(config.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(config.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(config.getConnectTimeout(), TimeUnit.SECONDS)
                .addInterceptor(getHeaderInterceptor(apiType))
                .addInterceptor(new LoggingInterceptor()).build();
        }

        private static Interceptor getHeaderInterceptor(ApiType apiType) {

            HeaderInterceptor headerInterceptor = null;
            switch (apiType) {
                case SNAP_B2B2C_TOKEN:
                    headerInterceptor = new HeaderInterceptor(new SNAPApplyTokenHeaderProvider());
                    break;
                case SNAP_B2B2C:
                    headerInterceptor = new HeaderInterceptor(new SNAPB2B2CHeaderProviderImpl());
                    break;
                case SNAP_B2B:
                default:
                    headerInterceptor = new HeaderInterceptor(new SNAPBasicHeaderProviderImpl());
                    break;
            }

            return headerInterceptor;
        }
    }
}
