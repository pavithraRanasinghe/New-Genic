package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.user.request.UserDetailDTO;
import lk.robot.newgenic.dto.user.request.UserSignUpDTO;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> signUp(UserSignUpDTO userSignUpDTO);

    ResponseEntity<?> logIn(AuthenticationRequest authenticationRequest);

    ResponseEntity<?> updateUser(UserDetailDTO userDetailDTO,long userId);

    ResponseEntity<?> getProfile(long userId);
}
