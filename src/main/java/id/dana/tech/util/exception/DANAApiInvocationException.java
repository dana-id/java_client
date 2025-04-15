/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util.exception;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANAApiInvocationException.java, v 0.1 2025‐04‐07 02.41 SLW Exp $$
 */
public class DANAApiInvocationException extends DANAApiException {

    public DANAApiInvocationException(String cause) {
        super("Invocation failed for API method: " + cause);
    }

    public DANAApiInvocationException(String methodName, Throwable cause) {
        super("Invocation failed for API method: " + methodName, cause);
    }
}