
package id.dana.tech.model.generated.api.info;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;




@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdditionalInfo extends LinkedHashMap<String, Object> {

    public String                     deviceId;
    public String                     channel;
    public Order                      order;
    public Boolean                    supportDeepLinkCheckoutUrl;
    public String                     phoneNumber;
    public String                     publicUserId;
    public String                     productCode;
    public String                     mcc;
    public ServiceInfo                serviceInfo;
    public String                     extendInfo;
}
