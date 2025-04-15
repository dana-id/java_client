/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service.client.header;

import okhttp3.Request;

import java.util.Map;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: HeaderProvider.java, v 0.1 2025‐03‐27 03.32 SLW Exp $$
 */
public interface HeaderProvider {

    Map<String, String> getHeaders(Request request, String requestBody);
}