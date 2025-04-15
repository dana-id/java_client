/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.client.enums;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: ApiType.java, v 0.1 2025‐04‐08 15.17 SLW Exp $$
 */
public enum ApiType {

    SNAP_B2B,
    SNAP_B2B2C,
    SNAP_B2B2C_TOKEN,
    SNAP_B2B_B2B2C,
    DANA_OPENAPI;

    public static ApiType getByName(String name) {
        for (ApiType env : ApiType.values()) {
            if (env.name().equalsIgnoreCase(name)) {
                return env;
            }
        }
        return null;
    }
}