/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util.exception;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANAApiMethodNotFoundException.java, v 0.1 2025‐04‐07 02.41 SLW Exp $$
 */
public class DANAApiMethodNotFoundException extends DANAApiException {

    public DANAApiMethodNotFoundException(String methodName) {
        super("No API method found with name: " + methodName);
    }
}