package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.Request.UserSignUpDTO;
import lk.robot.newgenic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
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
}
