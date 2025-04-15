/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service.client.interceptor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import id.dana.tech.model.client.DANAApiConfig;
import id.dana.tech.util.RequestUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: LoggingInterceptor.java, v 0.1 2025‐03‐26 18.36 SLW Exp $$
 */
public class LoggingInterceptor implements Interceptor {

    private static final Logger DIGEST_LOGGER   = LoggerFactory.getLogger("DANA_API_DIGEST_LOGGER");
    private static final Logger REQ_RESP_LOGGER = LoggerFactory
        .getLogger("DANA_API_REQ_RESP_LOGGER");

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String requestBody = RequestUtil.peekRequestBody(request,
            DANAApiConfig.get().getMaxBodyLengthForLogging());

        REQ_RESP_LOGGER.info("{} {},Req={},H={}", request.method(), request.url(), requestBody,
            request.headers().toMultimap());

        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(request);
        long timeCost = System.currentTimeMillis() - startTime;

        String responseBody = response.peekBody(DANAApiConfig.get().getMaxBodyLengthForLogging())
            .string();
        REQ_RESP_LOGGER.info("{} {},Resp={}", request.method(), request.url(), responseBody);

        String status = response.isSuccessful() ? "Y" : "N";
        DIGEST_LOGGER.info("{} {},{}ms,{},{} {}", request.method(), request.url(), timeCost, status,
            response.code(), response.message());

        return response;
    }
}