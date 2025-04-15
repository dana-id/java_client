/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.client.enums;

import lombok.Getter;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: SNAPHeader.java, v 0.1 2025‐03‐21 19.08 SLW Exp $$
 */
@Getter
public enum SNAPHeader {

                        CONTENT_TYPE("contentType", "Content-Type"),

                        AUTHORIZATION("authorization", "Authorization"),

                        AUTHORIZATION_CUSTOMER("authorizationCustomer", "Authorization-Customer"),

                        X_TIMESTAMP("timestamp", "X-TIMESTAMP"),

                        X_SIGNATURE("signature", "X-SIGNATURE"),

                        ORIGIN("origin", "ORIGIN"),

                        X_PARTNER_ID("partnerId", "X-PARTNER-ID"),

                        X_EXTERNAL_ID("externalId", "X-EXTERNAL-ID"),

                        X_DEVICE_ID("deviceId", "X-DEVICE-ID"),

                        X_IP_ADDRESS("ipAddress", "X-IP-ADDRESS"),

                        X_LATITUDE("latitude", "X-LATITUDE"),

                        X_LONGITUDE("longitude", "X-LONGITUDE"),

                        CHANNEL_ID("channelId", "CHANNEL-ID"),

                        HOST("host", "Host"),

                        CLIENT_KEY("clientKey", "X-CLIENT-KEY");

    private String code;

    private String headerName;

    private SNAPHeader(String code, String headerName) {
        this.code = code;
        this.headerName = headerName;
    }

    public static SNAPHeader getByCode(String code) {
        for (SNAPHeader action : values()) {
            if (action.getCode().equalsIgnoreCase(code)) {
                return action;
            }
        }
        return null;
    }
}