/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.client;

import id.dana.tech.util.CommonUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: Endpoint.java, v 0.1 2025‐04‐06 05.58 SLW Exp $$
 */
@Data
@RequiredArgsConstructor
public class ApiAction<REQ, RES> {

    private final Class<?>   apiClass;

    private final String     methodName;

    private String           serviceId;

    private final Class<RES> responseType;

    private final boolean    isSnap;

    /**
     * Getter method for property serviceId.
     *
     * @return property value of serviceId
     */
    public String getServiceId() {
        return CommonUtil.buildServiceId(apiClass, methodName);
    }
}
