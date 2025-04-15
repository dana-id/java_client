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
 * @version $Id: DirectDebitStatusRequest.java, v 0.1 2025‐04‐07 06.48 SLW Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectDebitStatusRequest {

    public String         originalPartnerReferenceNo;
    public String         originalReferenceNo;
    public String         originalExternalId;
    public String         serviceCode;
    public String         transactionDate;
    public Amount         amount;
    public String         merchantId;
    public String         subMerchantId;
    public String         externalStoreId;
    public AdditionalInfo additionalInfo;

}