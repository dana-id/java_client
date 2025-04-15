/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service.client.interceptor;

import java.io.IOException;
import java.util.Map;

import id.dana.tech.service.client.header.HeaderProvider;
import id.dana.tech.util.RequestUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: HeaderInterceptor.java, v 0.1 2025‐03‐27 03.30 SLW Exp $$
 */
public class HeaderInterceptor implements Interceptor {

    private final HeaderProvider headerProvider;

    public HeaderInterceptor(HeaderProvider headerProvider) {
        this.headerProvider = headerProvider;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        String requestBody = RequestUtil.peekRequestBody(request);
        Map<String, String> headers = headerProvider.getHeaders(request, requestBody);

        Request.Builder requestBuilder = request.newBuilder();
        headers.forEach(requestBuilder::addHeader);

        return chain.proceed(requestBuilder.build());
    }
}