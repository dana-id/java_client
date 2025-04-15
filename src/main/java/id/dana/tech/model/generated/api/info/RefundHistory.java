/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.generated.api.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: RefundHistory.java, v 0.1 2025‐04‐07 06.51 SLW Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefundHistory {

    public String refundNo;
    public String partnerRefundNo;
    public Amount refundAmount;
    public String refundStatus;
    public String refundDate;
    public String reason;
}