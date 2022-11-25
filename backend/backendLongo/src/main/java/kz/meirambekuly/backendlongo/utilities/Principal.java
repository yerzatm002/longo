package kz.meirambekuly.backendlongo.utilities;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class Principal {
    public static String getPhone() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
