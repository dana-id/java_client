/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.client.context;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: SNAPCustomerContext.java, v 0.1 2025‐04‐08 15.47 SLW Exp $$
 */
public class SNAPB2B2CContext {

    private static volatile String accessToken;

    private static volatile String refreshToken;

    private static volatile String latitude;

    private static volatile String longitude;

    private static volatile String ipAddress;

    private static volatile String deviceId;

    public static synchronized void setAccessToken(String token) {
        accessToken = token;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static synchronized void setRefreshToken(String token) {
        refreshToken = token;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    public static synchronized void setLatitude(String lat) {
        latitude = lat;
    }

    public static String getLatitude() {
        return latitude;
    }

    public static synchronized void setLongitude(String lon) {
        longitude = lon;
    }

    public static String getLongitude() {
        return longitude;
    }

    public static synchronized void setIpAddress(String ip) {
        ipAddress = ip;
    }

    public static String getIpAddress() {
        return ipAddress;
    }

    public static synchronized void setDeviceId(String id) {
        deviceId = id;
    }

    public static String getDeviceId() {
        return deviceId;
    }
}