/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.api.snap.directdebit;

import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitCancelRequest;
import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitPaymentRequest;
import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitStatusRequest;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitCancelResponse;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitPaymentResponse;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitStatusResponse;
import id.dana.tech.model.client.enums.ApiType;
import id.dana.tech.util.annotation.API;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DirectDebitAPIService.java, v 0.1 2025‐03‐20 20.10 SLW Exp $$
 */
@API(apiType = ApiType.SNAP_B2B)
public interface DirectDebitAPI {

    @POST("/rest/redirection/v1.0/debit/payment-host-to-host")
    Call<DirectDebitPaymentResponse> directDebitPayment(@Body DirectDebitPaymentRequest request);

    @POST("/rest/v1.1/debit/status")
    Call<DirectDebitStatusResponse> directDebitStatus(@Body DirectDebitStatusRequest request);

    @POST("/v1.0/debit/cancel.htm")
    Call<DirectDebitCancelResponse> directDebitCancel(@Body DirectDebitCancelRequest request);
}