
package id.dana.tech.model.generated.api.info;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceInfo {

    public String serviceType;
    public String serviceScenario;
    public String extendInfo;

}
