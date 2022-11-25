package kz.meirambekuly.backendlongo.web.dto.userDtos;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserDTO", description = "user info for registration")
public class UserCreatorDto{
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
}
