package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.Request.UserSignUpDTO;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.exception.CustomException;
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
            UserEntity userEntity = userRepository.findByUsername(username);
            if (userEntity == null){
                throw new UsernameNotFoundException(username);
            }
            List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+userEntity.getRole()));
            return new User(Long.toString(userEntity.getUserId()), userEntity.getPassword(), grantedAuthorities);
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
                userEntity.setRegisteredDate(DateConverter.localDateToUtil(LocalDate.now()));
                userEntity.setRegisteredTime(DateConverter.localTimeToUtil(LocalTime.now()));

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
}
