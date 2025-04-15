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
import id.dana.tech.util.DANASignatureUtil;
import id.dana.tech.util.DateTimeUtil;
import okhttp3.Request;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: SNAPApplyTokenHeaderProvider.java, v 0.1 2025‐04‐08 15.00 SLW Exp $$
 */
public class SNAPApplyTokenHeaderProvider implements HeaderProvider {

    @Override
    public Map<String, String> getHeaders(Request request, String requestBody) {

        Map<String, String> headers = new HashMap<>();
        headers.put(SNAPHeader.CONTENT_TYPE.getHeaderName(), "application/json");
        headers.put(SNAPHeader.X_TIMESTAMP.getHeaderName(), DateTimeUtil.getFormattedCurrentDateTime());
        headers.put(SNAPHeader.CLIENT_KEY.getHeaderName(), DANAApiConfig.get().getClientId());
        headers.put(SNAPHeader.X_SIGNATURE.getHeaderName(),
                DANASignatureUtil.SNAP.generateApplyTokenSignature(headers.get(SNAPHeader.X_TIMESTAMP.getHeaderName())));

        return headers;
    }
}