package kz.meirambekuly.backendlongo.services.impl;

import kz.meirambekuly.backendlongo.config.jwt.Jwt;
import kz.meirambekuly.backendlongo.config.passwordEncoder.PasswordEncoder;
import kz.meirambekuly.backendlongo.entity.Role;
import kz.meirambekuly.backendlongo.specifications.SmsVerfSpecifications;
import kz.meirambekuly.backendlongo.specifications.UserSpecifications;
import kz.meirambekuly.backendlongo.utilities.Constants;
import kz.meirambekuly.backendlongo.web.dto.ResultDto;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserCreatorDto;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserDetailsDto;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserLoginDto;
import kz.meirambekuly.backendlongo.entity.SmsVerification;
import kz.meirambekuly.backendlongo.entity.User;
import kz.meirambekuly.backendlongo.repositories.RoleRepository;
import kz.meirambekuly.backendlongo.repositories.SmsVerificationRepository;
import kz.meirambekuly.backendlongo.repositories.UserRepository;
import kz.meirambekuly.backendlongo.services.UserService;
import kz.meirambekuly.backendlongo.utilities.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.ls.LSInput;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * User Service create, login, update, get methods implementations
 * **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SmsVerificationRepository smsVerificationRepository;

    /**
     * Getting otp code for new user
     * **/
    @Async
    public Future<String> getOtp(String phoneNumber){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "https://smsc.kz/sys/send.php?login=info@sulu.life&psw=c708adb7a37585ca85de3ba573feb71aa1e57cf2&phones={phoneNumber}&mes=code&call=1";
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("phoneNumber", phoneNumber);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(url);
        try{
            HttpEntity<String> response = restTemplate.exchange(
                    uri.buildAndExpand(urlParams).toUri(),
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            String fullString = response.getBody().substring(response.getBody().indexOf("CODE"));
            String partString = fullString.substring(6);
            partString = partString.replaceFirst("", "");
//        String fulString= response.body.substring(response.body.indexOf("CODE"));
            return AsyncResult.forValue(partString);
        }catch (Exception e){
            System.out.println("Ocurred eerrroorr " + e);
        }
        return AsyncResult.forValue(null);
    }


    /**
     * User registration method implementation
     * **/
    @Override
    public ResultDto<?> register(UserCreatorDto dto) throws ExecutionException, InterruptedException {
        Optional<User> user = userRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (user.isPresent()){
            log.error(ErrorMessages.userWithPhoneNumberExists(dto.getPhoneNumber()));
            return ResultDto.builder()
                    .isSuccess(false)
                    .HttpStatus(HttpStatus.BAD_REQUEST.value())
                    .errorMessage(ErrorMessages.userWithPhoneNumberExists(dto.getPhoneNumber()))
                    .build();
        }
        Future<String> code = getOtp(dto.getPhoneNumber());
        SmsVerification smsVerification = SmsVerification.builder()
                    .phoneNumber(dto.getPhoneNumber())
                    .code(code.get())
                    .build();
        smsVerification = smsVerificationRepository.save(smsVerification);
        User newUser = User.builder()
                .phoneNumber(dto.getPhoneNumber())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .isActivated(false)
                .isEnabled(true)
                .createdAt(LocalDate.now())
                .password(PasswordEncoder.hashcode(dto.getPassword()))
                .role(new Role("User"))
                .build();
        newUser = userRepository.save(newUser);
        log.info("User with phone number: " + newUser.getPhoneNumber() + " successfully registered to the system");
        return ResultDto.builder()
                    .isSuccess(true)
                    .HttpStatus(HttpStatus.OK.value())
                    .data(newUser.getId())
                    .build();
    }

    /**
     * User login method
     * **/
    @Override
    public ResultDto<?> login(UserLoginDto dto) {
        Optional<User> user = userRepository.findOne(UserSpecifications
                .findByPhoneNumberAndPassword(dto.getPhoneNumber(), dto.getPassword()));
        Optional<SmsVerification> verification = smsVerificationRepository.findOne(SmsVerfSpecifications
                .findVerificationByPhoneNumber(dto.getPhoneNumber()));
        if(verification.isPresent()){
            log.debug("Non activated user: {} is trying to login", dto.getPhoneNumber());
            return ResultDto.builder()
                    .isSuccess(false)
                    .HttpStatus(HttpStatus.UNAUTHORIZED.value())
                    .errorMessage("Verify phone number")
                    .build();
        }else if(user.isPresent()){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.get().getRole().getName()));
            log.debug("User: {} successfully logged into the system", dto.getPhoneNumber());
            return ResultDto.builder()
                    .isSuccess(true)
                    .HttpStatus(HttpStatus.OK.value())
                    .data(Constants.PREFIX + Jwt.generateJwt(dto.getPhoneNumber(), authorities))
                    .build();
        }
        log.debug("User: {} is entering incorrect data", dto.getPhoneNumber());
        return ResultDto.builder()
                .isSuccess(false)
                .HttpStatus(HttpStatus.UNAUTHORIZED.value())
                .errorMessage(ErrorMessages.incorrectPassword())
                .build();
    }

    @Override
    public ResultDto<?> getUserInfo() {
        return null;
    }

    @Override
    public ResultDto<?> updateUser(UserDetailsDto dto) {
        return null;
    }
}
