/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import id.dana.tech.model.client.enums.ApiType;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANAAPI.java, v 0.1 2025‐03‐27 15.12 SLW Exp $$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface API {

    ApiType apiType();
}