/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service.client.header.impl;

import java.util.HashMap;
import java.util.Map;

import id.dana.tech.model.client.DANAApiConfig;
import id.dana.tech.model.client.enums.SNAPHeader;
import id.dana.tech.service.client.header.HeaderProvider;
import id.dana.tech.util.DateTimeUtil;
import id.dana.tech.util.RequestUtil;
import okhttp3.Request;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: BaseSNAPHeaderProviderImpl.java, v 0.1 2025‐03‐27 03.33 SLW Exp $$
 */
abstract class BaseSNAPHeaderProviderImpl implements HeaderProvider {

    @Override
    public Map<String, String> getHeaders(Request request, String requestBody) {

        Map<String, String> headers = new HashMap<>();
        headers.put(SNAPHeader.CONTENT_TYPE.getHeaderName(), "application/json");
        headers.put(SNAPHeader.X_TIMESTAMP.getHeaderName(), DateTimeUtil.getFormattedCurrentDateTime());
        headers.put(SNAPHeader.X_PARTNER_ID.getHeaderName(), DANAApiConfig.get().getClientId());
        headers.put(SNAPHeader.X_EXTERNAL_ID.getHeaderName(), RequestUtil.generateExternalId());
        headers.put(SNAPHeader.CHANNEL_ID.getHeaderName(), DANAApiConfig.get().getChannelId());

        return headers;
    }
}