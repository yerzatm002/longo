package kz.meirambekuly.backendlongo.specifications;

import kz.meirambekuly.backendlongo.entity.User;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;


/**
 * Searching user data from the db using exact arguments
 * **/
@UtilityClass
public class UserSpecifications {

    /**
     * Specification used to find user from db using phone number and password
     * **/
    public static Specification<User> findByPhoneNumberAndPassword(String phoneNumber, String password){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber),
                    criteriaBuilder.equal(root.get("password"), password)));
            return null;
        });
    }

    /**
     * Find user from the db using phone number
     * **/
    public static Specification<User> findByPhoneNumber(String phoneNumber){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));
    }

}
