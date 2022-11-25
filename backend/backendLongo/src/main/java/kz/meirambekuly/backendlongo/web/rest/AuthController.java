package kz.meirambekuly.backendlongo.web.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.meirambekuly.backendlongo.services.UserService;
import kz.meirambekuly.backendlongo.utilities.Constants;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserCreatorDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(Constants.PUBLIC_ENDPOINT + "/auth")
@RequiredArgsConstructor
@ApiModel(value = "Auth Controller", description = "Authentication Controller")
public class AuthController {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @ApiOperation(value = "user registration")
    public ResponseEntity<?> register (@ApiParam(value = "DTO for user registration")
                                       @RequestBody UserCreatorDto dto) throws ExecutionException, InterruptedException {
        log.debug("User with: {} trying to register", dto.getPhoneNumber());
        return ResponseEntity.ok(userService.register(dto));
    }

}
