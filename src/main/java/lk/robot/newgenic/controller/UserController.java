package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.user.request.UserDetailDTO;
import lk.robot.newgenic.dto.user.request.UserSignUpDTO;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import lk.robot.newgenic.service.UserService;
import lk.robot.newgenic.util.Utility;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private JavaMailSender mailSender;

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
        LOGGER.info("request - registeredUser | userUpdate | userDetail: {},userId: {}", userDetailDTO, principal.getName());
        long userId = Long.parseLong(principal.getName());
        ResponseEntity<?> updateUser = userService.updateUser(userDetailDTO, userId);
        LOGGER.info("response - registeredUser | userUpdate | updatedUser:{}",updateUser.getBody().toString());
        return updateUser;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal){
        LOGGER.info("request | getProfile | userId:{}",principal.getName());
        long userId = Long.parseLong(principal.getName());
        ResponseEntity<?> profile = userService.getProfile(userId);
        LOGGER.info("response | getProfile | profile: {}",profile.getBody().toString());
        return profile;
    }

    @PostMapping("/forgot/password")
    public ResponseEntity<?> resetPasswordProcess(HttpServletRequest request){
        String gmail = request.getParameter("gmail");
        String token = RandomString.make(45);

        userService.updateResetPasswordToken(token,gmail);

        String resetPasswordLink = Utility.getSiteUrl(request)+"/reset_password?token="+token;
        sendEmail(resetPasswordLink,gmail);

        return new ResponseEntity<>("Reset password link sent to email", HttpStatus.OK);
    }

    private void sendEmail(String resetPasswordLink, String gmail) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("pavithradev.xx@gmail.com","Developer support");
            helper.setTo(gmail);
            helper.setSubject("Here's a link to reset your password");
            String content = "<p>Hello,</p>" +
                    "<p>You have request to reset password</p>" +
                    "<p>Click link below to reset password</p>" +
                    "<p><b><a href=\""+resetPasswordLink+"\">Change my password</a></b></p>" +
                    "<p>Ignore this email if you do remember your password</p>";
            helper.setText(content,true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("reset_password")
    public ResponseEntity<?> checkResetToken(@Param(value = "token")String token){
        UserEntity userEntity = userService.get(token);
        if (userEntity!= null){
            return new ResponseEntity<>("Token valid to reset password",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Token invalid to reset password",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> resetPassword(HttpServletRequest request){
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        UserEntity userEntity = userService.get(token);
        if (userEntity!= null){
            userService.resetPassword(userEntity,password);
            return new ResponseEntity<>("Password reset successful",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Token invalid to reset password",HttpStatus.BAD_REQUEST);
        }
    }
}
