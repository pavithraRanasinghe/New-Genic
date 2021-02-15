package lk.robot.newgenic.configuration;

import lk.robot.newgenic.jwt.JwtConfig;
import lk.robot.newgenic.jwt.JwtTokenAuthorizationFilter;
import lk.robot.newgenic.oauth.CustomOAuth2UserService;
import lk.robot.newgenic.oauth.OAuth2LoginSuccessfulHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private CustomOAuth2UserService oAuth2UserService;
    @Autowired
    private OAuth2LoginSuccessfulHandler successfulHandler;

    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfig),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()

//                user permissions
                .antMatchers("/signUp").permitAll()
                .antMatchers("/logIn").permitAll()
//                product permissions
                .antMatchers("/product/**").permitAll()
//                cart permissions
                .antMatchers("/cart/**").permitAll()
//                category permissions
                .antMatchers("/category/**").permitAll()
//                feedback permissions
                .antMatchers(HttpMethod.GET,"/feedback/**").permitAll()
//                Question permissions
                .antMatchers(HttpMethod.GET,"/question/getQuestion").permitAll()
//                oauth2
                .antMatchers("/oauth2/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                    .loginPage(jwtConfig.getUrl())
                    .userInfoEndpoint().userService(oAuth2UserService)
                    .and()
                    .successHandler(successfulHandler);
    }
}
