package kz.meirambekuly.backendlongo.utilities;

import com.ibm.icu.text.Transliterator;
import kz.meirambekuly.backendlongo.entity.Role;
import kz.meirambekuly.backendlongo.entity.User;
import kz.meirambekuly.backendlongo.web.dto.RoleDto;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserDetailsDto;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectMapper {
    public static String convertCyryllicToLatin(String text) {
        Transliterator transliterator = Transliterator.getInstance("Cyrillic-Latin");
        return transliterator.transliterate(text);
    }

    public static RoleDto convertToRoleDto(Role role){
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static UserDto convertToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .image(user.getImage())
                .roleDto(convertToRoleDto(user.getRole()))
                .isActivated(user.getIsActivated())
                .isEnabled(user.getIsEnabled())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
