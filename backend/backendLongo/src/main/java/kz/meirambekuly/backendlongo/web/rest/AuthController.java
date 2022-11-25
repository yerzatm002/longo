package kz.meirambekuly.backendlongo.web.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.meirambekuly.backendlongo.services.UserService;
import kz.meirambekuly.backendlongo.utilities.Constants;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserCreatorDto;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(Constants.PUBLIC_ENDPOINT + "/auth")
@RequiredArgsConstructor
@ApiModel(value = "Auth Controller", description = "Authentication Controller")
public class AuthController {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    /**
     * @param dto User данные для того чтобы зарегистровать в нашем системе
     * @return userId зарегистрированного человека
     */
    @PostMapping("/register")
    @ApiOperation(value = "user registration")
    public ResponseEntity<?> register (@ApiParam(value = "DTO for user registration")
                                       @RequestBody UserCreatorDto dto) throws ExecutionException, InterruptedException {
        log.debug("User with: {} trying to register", dto.getPhoneNumber());
        return ResponseEntity.ok(userService.register(dto));
    }

    /**
     * @param dto User данные для того чтобы войти в нашу систему
     * @return jwt токен
     */
    @PostMapping("/login")
    @ApiOperation(value = "user login")
    public ResponseEntity<?> login (@ApiParam(value = "User login data: phone number and password")
                                    @RequestBody UserLoginDto dto){
        log.debug("User: {} is trying to login to the system", dto.getPhoneNumber());
        return ResponseEntity.ok(userService.login(dto));
    }


    /**
     * @param phoneNumber phone number of the User
     * @param code activation code of User
     * */
    @PostMapping("/activate")
    @ApiOperation(value = "user activation")
    public ResponseEntity<?> activate (@ApiParam(value = "User activation method")
                                       @RequestParam("phoneNumber")String phoneNumber,
                                       @RequestParam("code")String code){
        log.debug("User: {} is activating account", phoneNumber);
        return ResponseEntity.ok(userService.activateUser(phoneNumber, code));
    }

}
