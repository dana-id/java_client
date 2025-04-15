/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util;

import id.dana.tech.model.client.DANAApiConfig;
import id.dana.tech.model.client.enums.DANAEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: LogUtil.java, v 0.1 2025‐03‐28 02.52 SLW Exp $$
 */
public class LogUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger("DANA_API_LOGGER");

    public static void info(String message, Object... args) {
        LOGGER.info(message, args);
    }

    public static void error(String message, Object... args) {
        LOGGER.error(message, args);
    }

    public static void warn(String message, Object... args) {
        LOGGER.warn(message, args);
    }

    public static void debug(String message, Object... args) {
        if (DANAApiConfig.get().getEnvironment() == DANAEnvironment.SANDBOX) {
            LOGGER.info(message, args);
        }
    }
}