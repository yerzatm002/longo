package kz.meirambekuly.backendlongo.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.meirambekuly.backendlongo.services.UserService;
import kz.meirambekuly.backendlongo.utilities.Constants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.PRIVATE_ENDPOINT + "/account")
@RequiredArgsConstructor
public class AccountController {
    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final UserService userService;

    /**
     * Достать информацию залогиненного юзера
     *
     * @return Dto with user information
     */
    @GetMapping("")
    @Timed
    @ApiOperation(value = "getting logged-in user information")
    public ResponseEntity<?> account() {
        log.debug("Getting account information");
        return ResponseEntity.ok(userService.getUserInfo());
    }

    /**
     * Достать информацию залогиненного юзера
     *
     * @return Dto with user information
     */
    @PostMapping("/changePassword")
    @ApiOperation(value = "change user password")
    public ResponseEntity<?> changePassword(@ApiParam(value = "An old password and a new password")
                                            @RequestParam("oldPassword")String oldPassword,
                                            @RequestParam("newPassword")String newPassword) {
        log.debug("Changing user password");
        return ResponseEntity.ok(userService.changePassword(oldPassword, newPassword));
    }
}
