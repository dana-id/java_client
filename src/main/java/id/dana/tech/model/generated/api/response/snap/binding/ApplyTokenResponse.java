/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.generated.api.response.snap.binding;

import com.fasterxml.jackson.annotation.JsonInclude;
import id.dana.tech.model.generated.api.info.AdditionalInfo;
import id.dana.tech.model.generated.api.response.snap.BaseSnapAPIResponse;
import lombok.*;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: ApplyTokenResponse.java, v 0.1 2025‐04‐09 02.37 SLW Exp $$
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
public class ApplyTokenResponse extends BaseSnapAPIResponse {

    public String         accessToken;
    public String         tokenType;
    public String         accessTokenExpiryTime;
    public String         refreshToken;
    public String         refreshTokenExpiryTime;
    public AdditionalInfo additionalInfo;
}