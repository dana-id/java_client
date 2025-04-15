/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.generated.api.request.snap.directdebit;

import com.fasterxml.jackson.annotation.JsonInclude;
import id.dana.tech.model.generated.api.info.AdditionalInfo;
import id.dana.tech.model.generated.api.info.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DirectDebitCancelRequest.java, v 0.1 2025‐04‐07 06.55 SLW Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectDebitCancelRequest {

    public String         originalPartnerReferenceNo;
    public String         originalReferenceNo;
    public String         approvalCode;
    public String         originalExternalId;
    public String         merchantId;
    public String         subMerchantId;
    public String         reason;
    public String         externalStoreId;
    public Amount         amount;
    public AdditionalInfo additionalInfo;

}