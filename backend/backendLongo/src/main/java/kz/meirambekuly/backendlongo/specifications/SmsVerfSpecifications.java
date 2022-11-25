package kz.meirambekuly.backendlongo.specifications;

import kz.meirambekuly.backendlongo.entity.SmsVerification;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;


/**
 * Searching sms verification code from the db
 * **/
@UtilityClass
public class SmsVerfSpecifications {

    /**
     * Getting sms verification code from the db via phone number
     * **/
    public static Specification<SmsVerification> findVerificationByPhoneNumber(String phoneNumber) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));
    }


    /**
     * Checking whether code is correct for exact phone number sms verification
     * **/
    public static Specification<SmsVerification> findVerificationByPhoneNumberAndCode(String phoneNumber, String code) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber),
                    criteriaBuilder.equal(root.get("code"), code)));
            return null;
        });
    }

}
