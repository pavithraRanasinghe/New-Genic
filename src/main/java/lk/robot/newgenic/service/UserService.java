package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.request.UserDetailDTO;
import lk.robot.newgenic.dto.request.UserSignUpDTO;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.enums.AuthenticationProvider;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    ResponseEntity<?> signUp(UserSignUpDTO userSignUpDTO);

    ResponseEntity<?> logIn(AuthenticationRequest authenticationRequest);

    ResponseEntity<?> updateUser(UserDetailDTO userDetailDTO,String userId);

    ResponseEntity<?> updateProfilePicture(MultipartFile multipartFile,String userId);

    ResponseEntity<?> getProfile(String userId);

    ResponseEntity<?> newUserFromSuccessHandler(String email,String name,AuthenticationProvider authenticationProvider);

    ResponseEntity<?> updateUserFromSuccessHandler(UserEntity userEntity,String email,String name,AuthenticationProvider authenticationProvider);

    void updateResetPasswordToken(String token,String gmail);

    UserEntity get(String token);

    void resetPassword(UserEntity userEntity,String password);
}
