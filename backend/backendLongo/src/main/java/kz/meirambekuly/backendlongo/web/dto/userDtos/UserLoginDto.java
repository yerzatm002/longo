package kz.meirambekuly.backendlongo.web.dto.userDtos;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserLoginDTO", description = "mandatory fields to login user")
public class UserLoginDto {
    private String phoneNumber;
    private String password;
}
