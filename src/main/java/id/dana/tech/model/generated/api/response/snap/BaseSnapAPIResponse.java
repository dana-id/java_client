package id.dana.tech.model.generated.api.response.snap;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
public class BaseSnapAPIResponse {

    private String responseCode;
    private String responseMessage;
}
