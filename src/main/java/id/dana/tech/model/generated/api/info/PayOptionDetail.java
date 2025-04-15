
package id.dana.tech.model.generated.api.info;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;







@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayOptionDetail {

    public String         payMethod;
    public String         payOption;
    public Amount         transAmount;
    public Amount         feeAmount;
    public String         cardToken;
    public String         merchantToken;
    public AdditionalInfo additionalInfo;
}
