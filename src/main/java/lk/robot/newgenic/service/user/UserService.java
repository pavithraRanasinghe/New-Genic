package lk.robot.newgenic.service.user;

import lk.robot.newgenic.dto.user.Request.UserDetailDTO;
import lk.robot.newgenic.dto.user.Request.UserSignUpDTO;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<?> signUp(UserSignUpDTO userSignUpDTO);

    public ResponseEntity<?> logIn(AuthenticationRequest authenticationRequest);

    ResponseEntity<?> updateUser(UserDetailDTO userDetailDTO,long userId);
}
