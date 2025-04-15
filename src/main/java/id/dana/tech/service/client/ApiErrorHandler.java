/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service.client;

import java.lang.reflect.InvocationTargetException;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.dana.tech.model.client.ApiAction;
import id.dana.tech.util.LogUtil;
import okhttp3.ResponseBody;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: ApiErrorHandler.java, v 0.1 2025‐04‐07 04.01 SLW Exp $$
 */
public class ApiErrorHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <REQ, RES> RES parseErrorBody(ResponseBody errorBody,
                                                ApiAction<REQ, RES> apiAction) throws Exception {
        if (errorBody == null) {
            return null;
        }

        String rawBody = errorBody.string();
        try {
            if (apiAction.isSnap()) {
                return objectMapper.readValue(rawBody, apiAction.getResponseType());
            } else {
                return buildGenericErrorResponse(rawBody, apiAction);
            }
        } catch (Exception e) {
            LogUtil.warn("[ApiErrorHandler] Failed to parse error body: " + e.getMessage());
            return buildGenericErrorResponse(rawBody, apiAction);
        }
    }

    private static <REQ, RES> RES buildGenericErrorResponse(String errorBody,
                                                            ApiAction<REQ, RES> apiAction) throws NoSuchMethodException,
                                                                                           InvocationTargetException,
                                                                                           InstantiationException,
                                                                                           IllegalAccessException {

        RES fallback = apiAction.getResponseType().getDeclaredConstructor().newInstance();
        return fallback;
    }
}