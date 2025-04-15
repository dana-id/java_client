
package id.dana.tech.model.generated.api.info;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Good {

    public String unit;
    public String category;
    public Amount price;
    public String merchantShippingId;
    public String merchantGoodsId;
    public String description;
    public String snapshotUrl;
    public String quantity;
    public String extendInfo;

}
