/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service.client.header.impl;

import id.dana.tech.model.client.enums.SNAPHeader;
import id.dana.tech.util.DANASignatureUtil;
import okhttp3.Request;

import java.util.Map;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: SimpleSNAPHeaderProviderImpl.java, v 0.1 2025‐04‐08 14.59 SLW Exp $$
 */
public class SNAPBasicHeaderProviderImpl extends BaseSNAPHeaderProviderImpl {

    @Override
    public Map<String, String> getHeaders(Request request, String requestBody) {

        Map<String, String> baseHeaders = super.getHeaders(request, requestBody);
        baseHeaders.put(SNAPHeader.X_SIGNATURE.getHeaderName(),
            DANASignatureUtil.SNAP.generateAsymmetricSignature(requestBody, request.method(),
                request.url().encodedPath(),
                baseHeaders.get(SNAPHeader.X_TIMESTAMP.getHeaderName())));

        return baseHeaders;
    }
}