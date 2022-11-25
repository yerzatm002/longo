package kz.meirambekuly.backendlongo.repositories;

import kz.meirambekuly.backendlongo.entity.SmsVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SmsVerificationRepository extends JpaRepository<SmsVerification, Long>
        , JpaSpecificationExecutor<SmsVerification> {
}