package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.Request.UserSignUpDTO;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import lk.robot.newgenic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> userSignUp(@RequestBody UserSignUpDTO userSignUpDTO) {
        ResponseEntity<?> responseEntity = userService.signUp(userSignUpDTO);
        return responseEntity;
    }

    @PostMapping("/logIn")
    public ResponseEntity<?> userLogin(@RequestBody AuthenticationRequest authenticationRequest){
        ResponseEntity<?> responseEntity = userService.logIn(authenticationRequest);
        return responseEntity;
    }
}
