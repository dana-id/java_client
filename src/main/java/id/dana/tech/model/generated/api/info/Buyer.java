
package id.dana.tech.model.generated.api.info;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Buyer {

    public String externalUserType;
    public String nickname;
    public String externalUserId;
    public String userId;

}
