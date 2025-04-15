
package id.dana.tech.model.generated.api.request.snap.directdebit;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import id.dana.tech.model.generated.api.info.AdditionalInfo;
import id.dana.tech.model.generated.api.info.Amount;
import id.dana.tech.model.generated.api.info.PayOptionDetail;
import id.dana.tech.model.generated.api.info.UrlParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectDebitPaymentRequest {

    public String                partnerReferenceNo;
    public String                bankCardToken;
    public String                chargeToken;
    public String                otp;
    public String                merchantId;
    public String                terminalId;
    public String                journeyId;
    public String                subMerchantId;
    public Amount                amount;
    public List<UrlParam>        urlParams;
    public String                externalStoreId;
    public String                validUpTo;
    public String                pointOfInitiation;
    public String                feeType;
    public String                disabledPayMethods;
    public List<PayOptionDetail> payOptionDetails;
    public AdditionalInfo        additionalInfo;
}
