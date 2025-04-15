/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util.exception;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANAApiException.java, v 0.1 2025‐04‐07 02.40 SLW Exp $$
 */
public class DANAApiException extends RuntimeException {

    public DANAApiException(String message) {
        super(message);
    }

    public DANAApiException(String message, Throwable cause) {
        super(message, cause);
    }
}