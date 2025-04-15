
package id.dana.tech.model.generated.api.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingInfo {

    public Amount chargeAmount;
    public String lastName;
    public String trackingNo;
    public String countryName;
    public String merchantShippingId;
    public String cityName;
    public String address1;
    public String address2;
    public String phoneNo;
    public String areaName;
    public String email;
    public String zipCode;
    public String stateName;
    public String faxNo;
    public String carrier;
    public String firstName;
    public String mobileNo;

}
