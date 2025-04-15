/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: ConfigUtil.java, v 0.1 2025‐04‐07 11.57 SLW Exp $$
 */
public class ConfigUtil {

    public static String getConfig(String key, String defaultValue) {
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        String propertyValue = System.getProperty(key);
        return (propertyValue != null && !propertyValue.isEmpty()) ? propertyValue : defaultValue;
    }

    public static int getConfigInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(getConfig(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}