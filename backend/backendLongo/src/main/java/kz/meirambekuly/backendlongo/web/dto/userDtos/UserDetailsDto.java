package kz.meirambekuly.backendlongo.web.dto.userDtos;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserDetailsDTO", description = "details of user")
public class UserDetailsDto{
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Boolean isActivated;
    private Boolean isEnabled;
    private String image;
    private LocalDate createdAt;
}
