package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.user.request.UserDetailDTO;
import lk.robot.newgenic.dto.user.request.UserSignUpDTO;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.enums.AuthenticationProvider;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> signUp(UserSignUpDTO userSignUpDTO);

    ResponseEntity<?> logIn(AuthenticationRequest authenticationRequest);

    ResponseEntity<?> updateUser(UserDetailDTO userDetailDTO,String userId);

    ResponseEntity<?> getProfile(String userId);

    ResponseEntity<?> newUserFromSuccessHandler(String email,String name,AuthenticationProvider authenticationProvider);

    ResponseEntity<?> updateUserFromSuccessHandler(UserEntity userEntity,String email,String name,AuthenticationProvider authenticationProvider);

    void updateResetPasswordToken(String token,String gmail);

    UserEntity get(String token);

    void resetPassword(UserEntity userEntity,String password);
}
