package lk.robot.newgenic.service.admin;

import lk.robot.newgenic.dto.admin.request.UserRequestDTO;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<?> adminSignUp(UserRequestDTO userRequestDTO);

    ResponseEntity<?> adminSignIn(AuthenticationRequest authenticationRequest);
}
