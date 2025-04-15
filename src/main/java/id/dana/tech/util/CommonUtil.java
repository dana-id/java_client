/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: CommonUtil.java, v 0.1 2025‐04‐09 03.46 SLW Exp $$
 */
public class CommonUtil {

    public static String buildServiceId(Class<?> className, String methodName) {
        return className.getName() + "#" + methodName;
    }
}