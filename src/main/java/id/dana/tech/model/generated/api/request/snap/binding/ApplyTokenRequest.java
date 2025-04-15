/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.generated.api.request.snap.binding;

import com.fasterxml.jackson.annotation.JsonInclude;
import id.dana.tech.model.generated.api.info.AdditionalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: ApplyTokenRequest.java, v 0.1 2025‐04‐09 02.33 SLW Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplyTokenRequest {

    public String grantType;
    public String authCode;
    public String refreshToken;
    public AdditionalInfo additionalInfo;
}