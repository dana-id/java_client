/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.generated.constant;

import id.dana.tech.api.snap.binding.AccountBindingAPI;
import id.dana.tech.api.snap.directdebit.DirectDebitAPI;
import id.dana.tech.model.generated.api.request.snap.binding.ApplyTokenRequest;
import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitCancelRequest;
import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitPaymentRequest;
import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitStatusRequest;
import id.dana.tech.model.generated.api.response.snap.binding.ApplyTokenResponse;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitCancelResponse;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitPaymentResponse;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitStatusResponse;
import id.dana.tech.model.client.ApiAction;

import java.util.Map;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: API.java, v 0.1 2025‐04‐06 06.00 SLW Exp $$
 */
public class DANAApi {

    public static class SNAP {

        public static final ApiAction<DirectDebitPaymentRequest, DirectDebitPaymentResponse> DIRECT_DEBIT_PAYMENT = new ApiAction<>(
            DirectDebitAPI.class, "directDebitPayment", DirectDebitPaymentResponse.class, true);

        public static final ApiAction<DirectDebitStatusRequest, DirectDebitStatusResponse>   DIRECT_DEBIT_STATUS  = new ApiAction<>(
            DirectDebitAPI.class, "directDebitStatus", DirectDebitStatusResponse.class, true);

        public static final ApiAction<DirectDebitCancelRequest, DirectDebitCancelResponse>   DIRECT_DEBIT_CANCEL  = new ApiAction<>(
            DirectDebitAPI.class, "directDebitCancel", DirectDebitCancelResponse.class, true);

        public static final ApiAction<ApplyTokenRequest, ApplyTokenResponse>                 APPLY_TOKEN          = new ApiAction<>(
            AccountBindingAPI.class, "applyToken", ApplyTokenResponse.class, true);
    }
}