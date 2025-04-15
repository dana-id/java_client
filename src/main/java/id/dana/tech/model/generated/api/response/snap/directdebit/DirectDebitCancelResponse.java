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
 * @version $Id: DirectDebitCancelResponse.java, v 0.1 2025‐04‐07 06.56 SLW Exp $$
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
public class DirectDebitCancelResponse extends BaseSnapAPIResponse {

    public String         originalPartnerReferenceNo;
    public String         originalReferenceNo;
    public String         originalExternalId;
    public String         cancelTime;
    public String         transactionDate;
    public AdditionalInfo additionalInfo;

}