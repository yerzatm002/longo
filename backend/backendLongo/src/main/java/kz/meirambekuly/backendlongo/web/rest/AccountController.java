package kz.meirambekuly.backendlongo.web.rest;

import com.codahale.metrics.annotation.Timed;
import kz.meirambekuly.backendlongo.services.UserService;
import kz.meirambekuly.backendlongo.utilities.Constants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> account() {
        log.debug("Getting account information");
        return ResponseEntity.ok(userService.getUserInfo());
    }
}
