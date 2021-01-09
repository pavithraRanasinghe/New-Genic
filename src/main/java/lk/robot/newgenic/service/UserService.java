package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.Request.UserSignUpDTO;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<?> signUp(UserSignUpDTO userSignUpDTO);

    public ResponseEntity<?> logIn(AuthenticationRequest authenticationRequest);
}
