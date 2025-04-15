/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.dana.tech.model.generated.api.info.AdditionalInfo;
import id.dana.tech.model.generated.api.info.Amount;
import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitCancelRequest;
import id.dana.tech.model.generated.api.response.snap.binding.ApplyTokenResponse;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitPaymentResponse;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: ReqRespTest.java, v 0.1 2025‐04‐09 03.06 SLW Exp $$
 */
public class ReqRespTest {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void testAdditionalInfo1() {
        // Test the DirectDebitCancelRequest class
        DirectDebitCancelRequest request = new DirectDebitCancelRequest();
        request.setOriginalPartnerReferenceNo("123456");
        request.setOriginalReferenceNo("654321");
        request.setApprovalCode("APPROVED");
        request.setOriginalExternalId("EXT123");
        request.setMerchantId("MERCHANT123");
        request.setSubMerchantId("SUBMERCHANT123");
        request.setReason("Test reason");
        request.setExternalStoreId("STORE123");
        request.setAmount(new Amount("1000", "IDR"));

        AdditionalInfo add = new AdditionalInfo();
        add.put("additionalKey", "additionalValue");
        add.setDeviceId("DEVICE123");
        request.setAdditionalInfo(add);

        System.out.println(gson.toJson(request));
    }

    @Test
    public void testAdditionalInfo2() {

        String json = "{\n" + "    \"responseCode\": \"2007400\",\n"
                      + "    \"responseMessage\": \"Successful\",\n"
                      + "    \"accessToken\": \"SQoHkw1tSfWsULjf3qrWpPqimAQi6IxcgmvO4200\",\n"
                      + "    \"tokenType\": \"Bearer\",\n"
                      + "    \"accessTokenExpiryTime\": \"2031-11-02T11:31:19+07:00\",\n"
                      + "    \"refreshToken\": \"NEcnzX7Aq2vv5Ot08ZDSmCzfO4aEWhnWTpbf4200\",\n"
                      + "    \"refreshTokenExpiryTime\": \"2031-11-02T11:31:19+07:00\",\n"
                      + "    \"additionalInfo\": {\n" + "        \"userInfo\": {\n"
                      + "            \"publicUserId\": \"21779009320193133\"\n" + "        }\n"
                      + "    }\n" + "}";

        try {
            ApplyTokenResponse resp = new ObjectMapper().readValue(json, ApplyTokenResponse.class);
            System.out.println(gson.toJson(resp));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAdditionalInfo3() {

        String json = "{\n" + "   \"responseCode\":\"20054000\",\n"
                      + "   \"responseMessage\":\"Request has been processed successfully\",\n"
                      + "   \"referenceNo\":\"2020102977770000000009\",\n"
                      + "   \"partnerReferenceNo\":\"2020102900000000000001\",\n"
                      + "   \"approvalCode\":\"201039000200\",\n"
                      + "   \"appRedirectUrl\":\"https://pjsp.com/app?bizNo=REF993883&...\",\n"
                      + "   \"webRedirectUrl\":\"https://pjsp.com/universal?bizNo=REF993883&...\",\n"
                      + "   \"additionalInfo\":{\n" + "      \"deviceId\":\"12345679237\",\n"
                      + "      \"channel\":\"mobilephone\",\n" + "        \"userInfo\": {\n"
                      + "            \"publicUserId\": \"21779009320193133\"\n" + "        }\n"
                      + "   }\n" + "}";

        try {
            DirectDebitPaymentResponse resp = new ObjectMapper().readValue(json,
                DirectDebitPaymentResponse.class);
            System.out.println(gson.toJson(resp));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAdditionalInfo4() {

        String json = "{\n" + "   \"responseCode\":\"20054000\",\n"
                      + "   \"responseMessage\":\"Request has been processed successfully\",\n"
                      + "   \"referenceNo\":\"2020102977770000000009\",\n"
                      + "   \"partnerReferenceNo\":\"2020102900000000000001\",\n"
                      + "   \"approvalCode\":\"201039000200\",\n"
                      + "   \"appRedirectUrl\":\"https://pjsp.com/app?bizNo=REF993883&...\",\n"
                      + "   \"webRedirectUrl\":\"https://pjsp.com/universal?bizNo=REF993883&...\",\n"
                      + "   \"additionalInfo\":{\n" + "      \"deviceId\":\"12345679237\",\n"
                      + "      \"channel\":\"mobilephone\"\n" + "   }\n" + "}";

        try {
            DirectDebitPaymentResponse resp = new ObjectMapper().readValue(json,
                DirectDebitPaymentResponse.class);
            System.out.println(gson.toJson(resp));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}