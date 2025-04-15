/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service.client.header.impl;

import java.util.Map;

import id.dana.tech.model.client.context.SNAPB2B2CContext;
import id.dana.tech.model.client.enums.SNAPHeader;
import id.dana.tech.util.StringUtil;
import okhttp3.Request;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: SNAPB2B2CHeaderProviderImpl.java, v 0.1 2025‐04‐08 15.33 SLW Exp $$
 */
public class SNAPB2B2CHeaderProviderImpl extends SNAPBasicHeaderProviderImpl {

    @Override
    public Map<String, String> getHeaders(Request request, String requestBody) {

        Map<String, String> baseHeaders = super.getHeaders(request, requestBody);
        baseHeaders.put(SNAPHeader.AUTHORIZATION_CUSTOMER.getHeaderName(), "Bearer " + SNAPB2B2CContext.getAccessToken());
        baseHeaders.put(SNAPHeader.X_DEVICE_ID.getHeaderName(), SNAPB2B2CContext.getDeviceId());

        //below this optional in SNAP B2B2C API
        if (StringUtil.isNotBlank(SNAPB2B2CContext.getIpAddress())) {
            baseHeaders.put(SNAPHeader.X_IP_ADDRESS.getHeaderName(), SNAPB2B2CContext.getIpAddress());
        }

        if (StringUtil.isNotBlank(SNAPB2B2CContext.getLatitude())) {
            baseHeaders.put(SNAPHeader.X_LATITUDE.getHeaderName(), SNAPB2B2CContext.getLatitude());
        }

        if (StringUtil.isNotBlank(SNAPB2B2CContext.getLongitude())) {
            baseHeaders.put(SNAPHeader.X_LONGITUDE.getHeaderName(), SNAPB2B2CContext.getLongitude());
        }

        return baseHeaders;
    }
}