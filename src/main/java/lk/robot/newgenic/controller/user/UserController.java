package lk.robot.newgenic.controller.user;

import lk.robot.newgenic.dto.user.Request.UserDetailDTO;
import lk.robot.newgenic.dto.user.Request.UserSignUpDTO;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import lk.robot.newgenic.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/user")
public class UserController {

    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> userSignUp(@RequestBody UserSignUpDTO userSignUpDTO) {
        LOGGER.info("request - publicUser | userSignUp | userSignUpDetails:{}", userSignUpDTO);
        ResponseEntity<?> responseEntity = userService.signUp(userSignUpDTO);
        LOGGER.info("response - publicUser | userSignUp | userSignUpResponse:{}", responseEntity.getBody().toString());
        return responseEntity;
    }

    @PostMapping("/logIn")
    public ResponseEntity<?> userLogin(@RequestBody AuthenticationRequest authenticationRequest) {
        LOGGER.info("request - registeredUser | userSignIn | authenticationRequest:{}", authenticationRequest);
        ResponseEntity<?> responseEntity = userService.logIn(authenticationRequest);
        LOGGER.info("response - registeredUser | userSignIn | userSignIn:{}", responseEntity.getBody().toString());
        return responseEntity;
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateDetail(@RequestBody UserDetailDTO userDetailDTO,
                                          Principal principal) {
        LOGGER.info("request - registeredUser | userUpdate | userDetail: {},userid: {}", userDetailDTO, principal.getName());
        long userId = Long.parseLong(principal.getName());
        ResponseEntity<?> updateUser = userService.updateUser(userDetailDTO, userId);
        LOGGER.info("response - registeredUser | userUpdate | updatedUser:{}",updateUser.getBody().toString());
        return updateUser;
    }
}
