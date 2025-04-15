/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.generated.api.response.snap.directdebit;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import id.dana.tech.model.generated.api.info.AdditionalInfo;
import id.dana.tech.model.generated.api.info.Amount;
import id.dana.tech.model.generated.api.info.RefundHistory;
import id.dana.tech.model.generated.api.response.snap.BaseSnapAPIResponse;
import lombok.*;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DirectDebitStatusResponse.java, v 0.1 2025‐04‐07 06.50 SLW Exp $$
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
public class DirectDebitStatusResponse extends BaseSnapAPIResponse {

    public String              originalPartnerReferenceNo;
    public String              originalReferenceNo;
    public String              approvalCode;
    public String              originalExternalId;
    public String              serviceCode;
    public String              latestTransactionStatus;
    public String              transactionStatusDesc;
    public String              originalResponseCode;
    public String              originalResponseMessage;
    public String              sessionId;
    public String              requestId;
    public List<RefundHistory> refundHistory;
    public Amount              transAmount;
    public Amount              feeAmount;
    public String              paidTime;
    public AdditionalInfo      additionalInfo;

}