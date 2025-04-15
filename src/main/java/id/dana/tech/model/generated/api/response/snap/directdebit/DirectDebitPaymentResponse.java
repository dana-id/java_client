/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.generated.api.response.snap.directdebit;

import com.fasterxml.jackson.annotation.JsonInclude;

import id.dana.tech.model.generated.api.info.AdditionalInfo;
import id.dana.tech.model.generated.api.response.snap.BaseSnapAPIResponse;
import lombok.*;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DirectDebitPaymentResponse.java, v 0.1 2025‐03‐26 18.59 SLW Exp $$
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
public class DirectDebitPaymentResponse extends BaseSnapAPIResponse {

    public String         referenceNo;
    public String         partnerReferenceNo;
    public String         approvalCode;
    public String         appRedirectUrl;
    public String         webRedirectUrl;
    public AdditionalInfo additionalInfo;
}