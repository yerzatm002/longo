package kz.meirambekuly.backendlongo.utilities;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;

@UtilityClass
public class Constants {
    public static final String PREFIX = "Bearer ";
    public static final String AUTHORIZATION_PREFIX = HttpHeaders.AUTHORIZATION;
    public static final String PUBLIC_ENDPOINT = "/public";
    public static final String ADMIN_ENDPOINT = "/admin";
    public static final String CRM_ENDPOINT = "/crm";
    public static final String PRIVATE_ENDPOINT = "/private";
}
