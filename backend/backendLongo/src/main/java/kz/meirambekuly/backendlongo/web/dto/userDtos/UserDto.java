package kz.meirambekuly.backendlongo.web.dto.userDtos;

import io.swagger.annotations.ApiModel;
import kz.meirambekuly.backendlongo.web.dto.RoleDto;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserDTO", description = "user info")
public class UserDto {
    private Long id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String image;
    private RoleDto roleDto;
    private boolean isEnabled;
    private boolean isActivated;
    private LocalDate createdAt;
}
