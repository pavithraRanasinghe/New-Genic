package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.Request.UserSignUpDTO;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import lk.robot.newgenic.jwt.JwtGenerator;
import lk.robot.newgenic.repository.UserRepository;
import lk.robot.newgenic.service.UserService;
import lk.robot.newgenic.util.DateConverter;
import lk.robot.newgenic.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            return null;
//            UserEntity userEntity = userRepository.findByUsername(username);
//            if (userEntity == null){
//                throw new UsernameNotFoundException(username);
//            }
//            List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
//            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+userEntity.getRole()));
//            return new User(Long.toString(userEntity.getUserId()), userEntity.getPassword(), grantedAuthorities);
        }catch (Exception e){
            throw new CustomException("User Login failed");
        }
    }

    @Override
    public ResponseEntity<?> signUp(UserSignUpDTO userSignUpDTO) {
        try {
            if (!userSignUpDTO.equals(null)) {
                UserEntity userEntity = EntityToDto.userDtoToEntity(userSignUpDTO);
                userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                userEntity.setRegisteredDate(DateConverter.localDateToSql(LocalDate.now()));
                userEntity.setRegisteredTime(DateConverter.localTimeToSql(LocalTime.now()));

                UserEntity save = userRepository.save(userEntity);

                if (save.equals(null)) {
                    return new ResponseEntity<>("User Sign up failed", HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>("User sign up successful", HttpStatus.OK);

            } else {
                return new ResponseEntity<>("User details not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new CustomException("User sign up failed");
        }
    }

    @Override
    public ResponseEntity<?> logIn(AuthenticationRequest authenticationRequest) {
        try {
            if (authenticationRequest.equals(null)){
                return new ResponseEntity<>("User credentials not found",HttpStatus.BAD_REQUEST);
            }
            Optional<UserEntity> userEntity = userRepository.validateUser(authenticationRequest.getUsername());
            if (!userEntity.isPresent()){
                return new ResponseEntity<>("Invalid login credential",HttpStatus.UNAUTHORIZED);
            }
            if (passwordEncoder.matches(authenticationRequest.getPassword(),userEntity.get().getPassword())){
                String accessToken = createAccessToken(userEntity.get());
                if (accessToken.isEmpty()){
                    return new ResponseEntity<>("Token not created",HttpStatus.FORBIDDEN);
                }
                return new ResponseEntity<>(accessToken,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Invalid login credential",HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public String createAccessToken(UserEntity userEntity){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+userEntity.getRole()));
        return JwtGenerator.generateToken(userEntity.getUsername(),Long.toString(userEntity.getUserId()),authorities);
    }
}
