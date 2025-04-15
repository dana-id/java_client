/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.api.snap.binding;

import java.util.Map;

import id.dana.tech.model.client.enums.ApiType;
import id.dana.tech.model.generated.api.request.snap.binding.ApplyTokenRequest;
import id.dana.tech.model.generated.api.response.snap.binding.ApplyTokenResponse;
import id.dana.tech.util.annotation.API;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: AccountBindingAPI.java, v 0.1 2025‐04‐08 16.02 SLW Exp $$
 */
public interface AccountBindingAPI {

    @API(apiType = ApiType.SNAP_B2B2C_TOKEN)
    @POST("/v1.0/access-token/b2b2c.htm")
    Call<ApplyTokenResponse> applyToken(@Body ApplyTokenRequest request);
}