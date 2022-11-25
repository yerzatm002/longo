package kz.meirambekuly.backendlongo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sms_verification")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsVerification extends AbstractBaseEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "phone_number")
    private String phoneNumber;
}
