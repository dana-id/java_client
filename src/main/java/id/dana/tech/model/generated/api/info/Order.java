
package id.dana.tech.model.generated.api.info;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    public String             merchantTransType;
    public String             orderTitle;
    public String             orderMemo;
    public String             createdTime;
    public List<Good>         goods;
    public Buyer              buyer;
    public List<ShippingInfo> shippingInfo;
    public Seller             seller;

}
