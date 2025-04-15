/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.client.enums;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANAEnvironment.java, v 0.1 2025‐03‐21 17.05 SLW Exp $$
 */
public enum DANAEnvironment {

    SANDBOX("https://api.sandbox.dana.id"),
    PRODUCTION("https://api.saas.dana.id");

    private final String url;

    DANAEnvironment(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public static DANAEnvironment getByName(String name) {
        for (DANAEnvironment env : DANAEnvironment.values()) {
            if (env.name().equalsIgnoreCase(name)) {
                return env;
            }
        }
        return null;
    }
}