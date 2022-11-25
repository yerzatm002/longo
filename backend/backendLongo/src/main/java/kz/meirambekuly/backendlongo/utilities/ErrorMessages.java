package kz.meirambekuly.backendlongo.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
    public static String userWithPhoneNumberExists(String phoneNumber) {
        return "Establishment with phone number=" + phoneNumber + " already exists";
    }

    public static String cantFindEntityById(Object object, Long id) {
        return "Cannot find " + object.getClass().getName() + " with id=" + id;
    }

    public static String cantFindEntityByEstablishmentId(Object object, Long establishmentId) {
        return "Cannot find " + object.getClass().getName() + " with establishment id=" + establishmentId;
    }

    public static String establishmentNotFoundByPhoneNumber(String phoneNumber) {
        return "Cannot find establishment with phone number =" + phoneNumber;
    }

    public static String incorrectPassword() {
        return "Incorrect password";
    }

    public static String requiredFieldIsEmpty(String fieldName) {
        return "Field " + fieldName + " is required";
    }

    public static String notFoundEstablishmentWithId(Long id) {
        return "Not found establishment with id = " + id;
    }

    public static String notFoundProductWithId(Long id) {
        return "Not found product with id = " + id;
    }

    public static String notFoundServiceWithId(Long id) {
        return "Not found service with id = " + id;
    }

    public static String notValidURL(String url) {
        return "Not valid URL := " + url;
    }

    public static String notFoundPhotoWithUrl(String photo) {
        return "Not found photo with url := " + photo;
    }

    public static final String NO_DATA_FOUND = "NO_DATA_FOUND";

    public static final String NOT_VALID_DATA = "NOT_VALID_DATA";
}
