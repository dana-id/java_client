/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import id.dana.tech.model.client.DANAApiConfig;
import id.dana.tech.model.client.enums.DANAEnvironment;
import id.dana.tech.model.generated.api.info.*;
import id.dana.tech.model.generated.api.request.snap.binding.ApplyTokenRequest;
import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitCancelRequest;
import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitPaymentRequest;
import id.dana.tech.model.generated.api.request.snap.directdebit.DirectDebitStatusRequest;
import id.dana.tech.model.generated.api.response.snap.binding.ApplyTokenResponse;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitCancelResponse;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitPaymentResponse;
import id.dana.tech.model.generated.api.response.snap.directdebit.DirectDebitStatusResponse;
import id.dana.tech.model.generated.constant.DANAApi;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANAApiServiceTest.java, v 0.1 2025‐03‐28 02.47 SLW Exp $$
 */
public class DANAApiServiceTest {

    @BeforeEach
    public void setUp() {
        // Reset the singleton instance before each test
        DANAApiConfig.reset();
    }

    @Test
    public void testDirectDebitPayment1() {
        DANAApiConfig.get();

        DirectDebitPaymentResponse res = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_PAYMENT,
            new DirectDebitPaymentRequest());
        System.out.println(new Gson().toJson(res));
    }

    @Test
    public void testDirectDebitPayment2() {

        DANAApiConfig.get();
        DirectDebitPaymentResponse res = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_PAYMENT,
            buildDirectDebitPaymentRequestSimple());

        System.out.println(new Gson().toJson(res));
    }

    @Test
    public void testDirectDebitPayment3() {
        DANAApiConfig.builder().clientId("2025021714245533502768").channelId("test-channel-id")
            .merchantPrivateKey(
                "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQDHqbvZCm8CAPDslGKvol/6gsQcndHoD+pisCJ4noVbxpD06iaM4EwiD3vOOLxNrdw1EwW/EAKw5jKF+P3pKlQS/XaJUf3AQ+Qk8rc31O4tvvl9H5uPcxwH4IjO95sA7Om4hp9DDC/KEKh2G5iahaux/pCwzByI99cSS17LP79n2bz9VBykILjMByW64Md4nIFpn7hE/XnpF++cCZ9fOVZjKDTbjQ3Odk5XCmrjkYECXQFW3kxDtQMWZ2DXxHDpsPc6dTjAhwhAB7BelFrB7q/f+mJAvwp0xa5kMluEcd7W/trcjHvdjX1v/CGRaMrf6+gHibRag0wkUR8GacFjcwdBAgMBAAECgf97fujJnkJ7IsniCwXfTLfA4xEaAiPLPczjrb4kjSg/U9plIk8l47YzexBAxabzBQ6nZVSwkN6ZPEkTxKaIib3m4Q9ocGZbfaKnl9jrl7Caav01/07gs3orBR6ZpV0ifWcy01G+ztLgUN1h8D+7k+JW/fXQ5O7haSqGDkhbvtpNCK6mvMldXezJmPLiGdMzvFQ5Vcp5r6TC+A0mN7zwD9Mwh6rC6m+twyS8x2WfhS4V94UtbdmSVPPCPbp7uqBPBnILC1Q2ID34mx0EryhLtDrxSQdCUQs7R3OavGJcA6HR5k/6IO6g76l8RO9Fc8C8/+lHEBRB8UtBltgLdrYlCQECgYEAx+BnfmeXMAjgQ7PeMObccFiyrBznP7WN6+qPqFYJ2IrkQrO+hxPwG/jV6eAOv78MRoJWa3vb9a7k3XIc/6ycQzzKSZSLSlflE1tGctcXFdDlEz3ckOmtqy5Ruxuc9bie8PvElJmyilzB81ZFa6lCw6uhziD9nBq/z6itnOP59sECgYEA/7n6guPSx2nxTN0/liFJ/qPVh703ogJzbg4az21Xr/YbQQ5cYOK5AdDdvaXsqo7psY2gvd49JoT0kHksmG8bQ6s7FMBxO4Bnl5u0sFLxBf3hXhi1bHspgCPXP2cgZ9iNEbznX9oFONKRXGTZ31XHXZuoRRgXaeSsLY08AZAZsIECgYBIeeKNP1rfHBjV897ZtJyyUDdZmZFw1JCFvyQgUjJ3xsVGOo8Xw+8I9Y37pDc06D23Q4O6rcrwp15tvfct7pUq/FwNmkN3za9O+YXuFnhUfM7Tz1avZUyICd+xDS45G5zmnSSHDcRjz4a0ic/aXiHLeezOJcBQXpK25d8uSKxXAQKBgQDaAHSjso4Ue6c+THqRhI1s68PztIXBse8vSgzm+aLAGcDAI/ryiTUIbEMsElAAUla8iy0Vz2g39y36H6K5zDXk6QsztJ6TB/LH1vAhRIO+BOFj8xJuiUwmtecgYMzKWlxgW7avCtBa0X8j0cG+V4Tesvyu8ku7dTqmlnkqJ+sOgQKBgC5ngdOdV6/AWAK7IrExT0VE4j3wc4U6W5dU7Uc3MrMs6cG3NMaCj5OY5lGcJ0qVaTRGU6Zme46Rlfhreh2eh35H+iNnmtUXlTJHqH/GxX99ss+niEa/gGgm4qIVSybM6A96JjGHODRPQ9/vCzOAVvUx1PHUA2CAp2fydKSkyPKs")
            .danaPublicKey(
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtsKx/SmAMcoF5oq7ns/McsA+z2hbcYvFeClYdjSg9+7A/0ms7XQj3mQYncLOEoXxt6/uduuPh99+hW+S9dnv0nAvkntIUAWGe9UGW4o8GP/Xx1jh7+uh46qoOPCQQJaK8qR1LxJE0Xa4kU93hku/SZKNBf3r2gkg4QR4pG+4Jk4Zyepa0Cgm15FsCGU35qsw2aNTxQY1ipDTNivVu8mfXeNHdZTS0eHW//k/FgBQsD0R45vN95rIZXffqIWd2I75SWkOJzAouF28a6d7rP2wvEV6dK27yVDjLhDsaYdHW50NBsigCMChuc1WOEZIBXpIyLn7sI8MS2EqDozjQ0dQWwIDAQAB")
            .connectTimeout(1000).environment(DANAEnvironment.SANDBOX).build();
        DirectDebitPaymentResponse res = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_PAYMENT,
            buildDirectDebitPaymentRequestSimple());

        System.out.println(new Gson().toJson(res));
    }

    @Test
    public void testDirectDebitPayment4() {

        DANAApiConfig.get();
        DirectDebitPaymentResponse res = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_PAYMENT,
            buildDirectDebitPaymentRequestComplete());

        System.out.println(new Gson().toJson(res));
    }

    @Test
    public void testDirectDebitPayment5() {

        DANAApiConfig.builder().clientId("2025021911172526843335").channelId("test-channel-id")
            .merchantPrivateKey(
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCdopUZFuKfhaHgo3fk4+HK0kGNhPPtBkpJmOYd+IfEWa6V9Pzuu1r4i8YnSsIGCD7US0qTWFfwbmA8YmzWG0nmMdrf+MKT2X6W7OOe5P1RWXAlqG/OYXD4igOgzuEg+Owu2ch7ThKBijS1kGkSaRC9QaGYc+pidQZJIwikMskO9vKvyC3CW6NrVJtGmGAMKhhVer65QnkfM9LTj1prFNKd9eH+pe4OgH0TMKuCmksNLa5uaTbX52vtZo7/dEWOfDHmi6/rD6ILVc4kpqvcJORfolXU5JJPUL2xs8bMwdnwAYHnUEzSaiTt1q8P/mKtST5/QLbk6DOTC3EC3zR2QOj9AgMBAAECggEAf2wc+dr19d0frWZ2VB+hNUIgmzUNuFuNUVC3MyLjHGUiKzWN/6yTLLZPxxM3I4kmwJTcA5819rRCNo2ZTLliUyGZu9Jp9vmoyndSx+5yln+ll8tAFaPOnhlyNVsFwJF22ahR1tqVOwFs+hdx19/2GAYligapQE04d4ZKRfCHdf/8kYRcObHL15/5LtQemHVh4ffBX5F6c1Tp7vF4icYh+zaYiwni6qnu3yBxac0XWq6pi0DT4GWjpKhFvFsEzhumX7NXsJGs59IN6bvPYKz6djlDIICM8ip2bevytilytnVcXiAwmHRKPJa6QD7b8oopcjXkdnFIZHpRzfnb5NsOQQKBgQD4z6OXYHmpvNT8MMMtyK345H+lGMg1gPUluUwErkO1wIP/MRgJx0QvDcTSHkxheCnvIIIvnA9RsF2T8tYPDFe4aLaw+FaRUNKrADRGUunJy/7bEl6G3rNkktTai6TzCPoaP4SLn4E5OoDAVlwa1sdMzgMgrYH0n6ycCCD54TZQdwKBgQCiMIyXUc9kaY0SI+rvZmnTZvXTipUzIEv89BcSpgOzApfohD66WZM90WO6akPbAjxmpmGoqvtDPpnm2M+w29RGLMF53WpKXKc3kx6KM6hM5TDtJLMiKIS4LmZ1VDqgY4ycNwFraVFxEeQWWweptz3YqKlV+5rT6igPeBi4dlADKwKBgQD1baxK9+lK22+kTF1a3idt6cdXjXh//nLTAVLREyzLLdmGcmDp1FuXppdNRIHHa4bB0GlamTArPjMwa0tnkJ8+0lvTeUO7F+SWtR7bd5IRcl40rov7sdV3EbdQ1IqjCWnbVmhiabZIQ0MZdpZoCxCQ3G4NX9le+dqEFyigE91FxQKBgFxBPVc9Y7h8CZBN+e6e1Ic5Q49GfsOMFW0W8qDa6bPDP+E0h/G7zfMVzaJVm69RwEWrUlV8nFuNmkEjBlBVWpNoFc1Z0oHl3f/QoUh/RbYnkAL0IyjE32lci08l0As3VN3us9IDIB4uzQyiTuOMpZM9rMSJz3HG+W6DI5yZP73tAoGBAPFwyl8a5M5N+qTWsvDQDNU//c/xVhPRzajcV2UKO2kxzJgvrF0LzvqVVC600gFB2LNcbEviDooYPUUYJWR8Mp6HpbBQquf/CBzop6DCE665iTXGukFKX9O4V9spEbk8eJfng3fDInZLS9wE/DplyVE8FUvo5D0fKZ1hKqLkRUSI")
            .danaPublicKey(
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtsKx/SmAMcoF5oq7ns/McsA+z2hbcYvFeClYdjSg9+7A/0ms7XQj3mQYncLOEoXxt6/uduuPh99+hW+S9dnv0nAvkntIUAWGe9UGW4o8GP/Xx1jh7+uh46qoOPCQQJaK8qR1LxJE0Xa4kU93hku/SZKNBf3r2gkg4QR4pG+4Jk4Zyepa0Cgm15FsCGU35qsw2aNTxQY1ipDTNivVu8mfXeNHdZTS0eHW//k/FgBQsD0R45vN95rIZXffqIWd2I75SWkOJzAouF28a6d7rP2wvEV6dK27yVDjLhDsaYdHW50NBsigCMChuc1WOEZIBXpIyLn7sI8MS2EqDozjQ0dQWwIDAQAB")
            .connectTimeout(1000).environment(DANAEnvironment.SANDBOX).build();

        DirectDebitPaymentResponse res = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_PAYMENT,
            buildDirectDebitPaymentRequestComplete());

        System.out.println(new Gson().toJson(res));
    }

    private DirectDebitPaymentRequest buildDirectDebitPaymentRequestSimple() {
        DirectDebitPaymentRequest req = new DirectDebitPaymentRequest();
        req.setPartnerReferenceNo("12313133123");

        return req;
    }

    private DirectDebitPaymentRequest buildDirectDebitPaymentRequestComplete() {

        // Amount
        Amount mainAmount = new Amount("10000.00", "IDR");

        // PayOptionDetails
        Amount feeAmount = new Amount("100.00", "IDR");
        Amount transAmount = new Amount("100.00", "IDR");
        PayOptionDetail payOptionDetail = new PayOptionDetail();
        payOptionDetail.setPayMethod("BALANCE");
        payOptionDetail.setPayOption("BALANCE");
        payOptionDetail.setFeeAmount(feeAmount);
        payOptionDetail.setTransAmount(transAmount);
        List<PayOptionDetail> payOptionDetails = new ArrayList<>();
        payOptionDetails.add(payOptionDetail);

        // UrlParams
        UrlParam payReturnUrl = new UrlParam(
            "http://finmock.tool.dana.id/mock/api/snap/finish/notify.htm", "PAY_RETURN", "N");
        UrlParam notificationUrl = new UrlParam(
            "http://finmock.tool.dana.id/mock/api/snap/finish/notify.htm", "NOTIFICATION", "N");
        List<UrlParam> urlParams = new ArrayList<>();
        urlParams.add(payReturnUrl);
        urlParams.add(notificationUrl);

        // Buyer
        Buyer buyer = new Buyer("BBM", "", "wxjtestbuyer001", "");

        // Good
        Amount goodPrice = new Amount("10.00", "IDR");
        Good good = new Good("Kg", "travelling/subway", goodPrice, "564314314574327545",
            "24525635625623",
            "Women Summer Dress New White Lace Sleeveless Cute Casual Summer Dresses Vestidos roupas femininas WQW1045 wkawjkakjwkajwkajwakjwkajwkajw nndahdhilcnnc",
            "[http://snap.url.com]", "", null);
        List<Good> goods = new ArrayList<>();
        goods.add(good);

        // Seller
        Seller seller = new Seller("", "mike", "", "");

        // ShippingInfo
        Amount chargeAmount = new Amount("20.00", "IDR");
        ShippingInfo shippingInfo = new ShippingInfo(chargeAmount, "Li", "646431431322332133", "JP",
            "564314314574327545", "Atlanta", "137 W San Bernardino", "4114 Sepulveda",
            "2423-2322342", "Rd", "abc@gmail.com", "310001", "GA", "2123-11113", "Federal Express",
            "Jim", "13765443223");
        List<ShippingInfo> shippingInfos = new ArrayList<>();
        shippingInfos.add(shippingInfo);

        // Order
        Order order = new Order("type",
            "Women Summer Dress New White Lace  Sleeveless Cute Casual Summer Dresses  Vestidos roupas femininas WQW1045",
            "Memo", "", goods, buyer, shippingInfos, seller);

        // AdditionalInfo
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setOrder(order);
        additionalInfo.setMcc("5732");
        additionalInfo.setProductCode("51051000100000000001");
        additionalInfo.setPublicUserId("");
        additionalInfo.setPhoneNumber("");
        additionalInfo.setSupportDeepLinkCheckoutUrl(false);
        additionalInfo.setExtendInfo("{\"skuName\": \"kkn_di_dana\"}");

        // EnvInfo (dynamic)
        HashMap<String, Object> envInfo = new HashMap<>();
        envInfo.put("appVersion", "1.0");
        envInfo.put("clientIp", "10.15.8.189");
        envInfo.put("clientKey", "e5806b64-598d-414f-b7f7-83f9576eb6fb");
        envInfo.put("extendInfo", "");
        envInfo.put("merchantAppVersion", "1.0");
        envInfo.put("orderOsType", "IOS");
        envInfo.put("orderTerminalType", "APP");
        envInfo.put("osType", "Windows.PC");
        envInfo.put("sdkVersion", "1.0");
        envInfo.put("sessionId", "8EU6mLl5mUpUBgyRFT4v7DjfQ3fcauthcenter");
        envInfo.put("sourcePlatform", "IPG");
        envInfo.put("terminalType", "APP");
        envInfo.put("tokenId", "a8d359d6-ca3d-4048-9295-bbea5f6715a6");
        envInfo.put("websiteLanguage", "en_US");

        additionalInfo.put("envInfo", envInfo);

        // Request
        DirectDebitPaymentRequest request = new DirectDebitPaymentRequest();
        request.setPartnerReferenceNo("zentest-4504260981");
        request.setMerchantId("216620000002993622833");
        request.setSubMerchantId("");
        request.setAmount(mainAmount);
        request.setDisabledPayMethods("");
        request.setExternalStoreId("");
        request.setValidUpTo("2026-01-01T11:11:11-07:00");
        request.setPayOptionDetails(payOptionDetails);
        request.setUrlParams(urlParams);
        request.setAdditionalInfo(additionalInfo);

        return request;
    }

    @Test
    public void testDirectDebitStatus1() {

        DANAApiConfig.get();

        ApplyTokenResponse tokenResponse = DANAApiService.call(DANAApi.SNAP.APPLY_TOKEN,
            new ApplyTokenRequest());
        System.out.println(new Gson().toJson(tokenResponse));

        DirectDebitStatusResponse statusResponse = DANAApiService
            .call(DANAApi.SNAP.DIRECT_DEBIT_STATUS, new DirectDebitStatusRequest());
        System.out.println(new Gson().toJson(statusResponse));
    }

    public static void main(String[] args) {

        DANAApiConfig.get();

        ApplyTokenResponse tokenResponse = DANAApiService.call(DANAApi.SNAP.APPLY_TOKEN,
            new ApplyTokenRequest());
        System.out.println(new Gson().toJson(tokenResponse));

        DirectDebitStatusResponse statusResponse = DANAApiService
            .call(DANAApi.SNAP.DIRECT_DEBIT_STATUS, new DirectDebitStatusRequest());
        System.out.println(new Gson().toJson(statusResponse));
    }

    @Test
    public void testDirectDebitStatus2() {

        DANAApiConfig.get();
        DirectDebitStatusResponse res = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_STATUS,
            buildDirectDebitStatusRequest());

        System.out.println(new Gson().toJson(res));
    }

    private DirectDebitStatusRequest buildDirectDebitStatusRequest() {
        DirectDebitStatusRequest request = new DirectDebitStatusRequest();

        request.setOriginalPartnerReferenceNo("2020102900000000000001");
        request.setOriginalReferenceNo("2020102977770000000009");
        request.setOriginalExternalId("30443786930722726463280097920912");
        request.setServiceCode("54");
        request.setTransactionDate("2020-12-21T14:56:11+07:00");

        Amount amount = new Amount();
        amount.setValue("12345678.00");
        amount.setCurrency("IDR");
        request.setAmount(amount);

        request.setMerchantId("23489182303312");
        request.setSubMerchantId("23489182303312");
        request.setExternalStoreId("183908924912387");

        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setDeviceId("12345679237");
        additionalInfo.setChannel("mobilephone");
        request.setAdditionalInfo(additionalInfo);

        return request;
    }

    @Test
    public void testDirectDebitCancel1() {

        DANAApiConfig.get();
        DirectDebitCancelResponse res = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_CANCEL,
            new DirectDebitCancelRequest());
        System.out.println(new Gson().toJson(res));

    }

    @Test
    public void testDirectDebitCancel2() {

        DANAApiConfig.get();
        DirectDebitCancelResponse res = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_CANCEL,
            buildDirectDebitCancelRequest());

        System.out.println(new Gson().toJson(res));
    }

    private DirectDebitCancelRequest buildDirectDebitCancelRequest() {
        DirectDebitCancelRequest request = new DirectDebitCancelRequest();

        request.setOriginalPartnerReferenceNo("2020102900000000000001");
        request.setOriginalReferenceNo("2020102977770000000009");
        request.setApprovalCode("201039000200");
        request.setOriginalExternalId("30443786930722726463280097920912");
        request.setMerchantId("23489182303312");
        request.setSubMerchantId("23489182303312");
        request.setReason("Network timeout");
        request.setExternalStoreId("124928924949487");

        Amount amount = new Amount();
        amount.setValue("10000.00");
        amount.setCurrency("IDR");
        request.setAmount(amount);

        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setDeviceId("12345679237");
        additionalInfo.setChannel("mobilephone");
        request.setAdditionalInfo(additionalInfo);

        return request;
    }

    @Test
    public void testApplyToken1() {

        DANAApiConfig.get();
        ApplyTokenResponse res = DANAApiService.call(DANAApi.SNAP.APPLY_TOKEN,
            buildApplyTokenRequest());

        System.out.println(new Gson().toJson(res));
    }

    private ApplyTokenRequest buildApplyTokenRequest() {
        ApplyTokenRequest request = new ApplyTokenRequest();
        request.setAuthCode("ABC3821738137123");
        request.setGrantType("AUTHORIZATION_CODE");
        request.setRefreshToken("");
        request.setAdditionalInfo(new AdditionalInfo());
        return request;
    }

    private Map<String, String> buildGetOauthUrlRequest() {
        String seamlessDataJson = "{\"externalUid\":\"085042ae-0c3f-465d-9590-595a9ce6d56f@blibli\",\"mobile\":\"083897951530\",\"reqTime\":\"2023-07-05T09:30:58+07:00\",\"verifiedTime\":\"0\",\"reqMsgId\":\"db1e42a4\",\"skipConsultRegister\":true}";

        Map<String, String> params = new HashMap<>();
        params.put("timestamp", "2020-12-23T09:10:11+07:00");
        params.put("partnerId", "21667842748173213");
        params.put("externalId", "637126721366372");
        params.put("channelId", "DANAID");
        params.put("state", "WOdkkwijSDs");
        params.put("scopes", "QUERY_BALANCE,PUBLIC_ID");
        params.put("redirectUrl", "https://domain.com/authSuccess.htm");
        params.put("seamlessData", seamlessDataJson); // already serialized string
        params.put("seamlessSign", "vbhdauyeuwqye63721313");

        return params;
    }
}