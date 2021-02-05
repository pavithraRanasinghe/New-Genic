package lk.robot.newgenic.oauth;

import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.enums.AuthenticationProvider;
import lk.robot.newgenic.repository.UserRepository;
import lk.robot.newgenic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessfulHandler extends SimpleUrlAuthenticationSuccessHandler {

    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Autowired
    private OAuth2LoginSuccessfulHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        UserEntity userEntity = userRepository.findByGmail(oAuth2User.getEmail());

        if (userEntity == null){
            userService.newUserFromSuccessHandler(oAuth2User.getEmail(),oAuth2User.getName(), AuthenticationProvider.FACEBOOK);
        }else{
            userService.updateUserFromSuccessHandler(userEntity,oAuth2User.getEmail(),oAuth2User.getName(), AuthenticationProvider.FACEBOOK);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
