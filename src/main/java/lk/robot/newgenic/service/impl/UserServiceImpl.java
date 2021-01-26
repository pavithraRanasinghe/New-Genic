package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.user.request.UserDetailDTO;
import lk.robot.newgenic.dto.user.request.UserSignUpDTO;
import lk.robot.newgenic.dto.user.response.SignInResponseDTO;
import lk.robot.newgenic.entity.UserAddressDetailEntity;
import lk.robot.newgenic.entity.UserAddressEntity;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.enums.AddressType;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import lk.robot.newgenic.jwt.JwtGenerator;
import lk.robot.newgenic.repository.UserAddressDetailRepository;
import lk.robot.newgenic.repository.UserAddressRepository;
import lk.robot.newgenic.repository.UserRepository;
import lk.robot.newgenic.service.UserService;
import lk.robot.newgenic.util.DateConverter;
import lk.robot.newgenic.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserAddressRepository userAddressRepository;
    private UserAddressDetailRepository userAddressDetailRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           UserAddressRepository userAddressRepository,
                           UserAddressDetailRepository userAddressDetailRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
        this.userAddressDetailRepository = userAddressDetailRepository;
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
        } catch (Exception e) {
            throw new CustomException("User sign up failed");
        }
    }

    @Override
    public ResponseEntity<?> logIn(AuthenticationRequest authenticationRequest) {
        try {
            if (authenticationRequest.equals(null)) {
                return new ResponseEntity<>("User credentials not found", HttpStatus.BAD_REQUEST);
            }
            Optional<UserEntity> userEntity = userRepository.validateUser(authenticationRequest.getUsername());
            if (!userEntity.isPresent()) {
                return new ResponseEntity<>("Invalid login credential", HttpStatus.UNAUTHORIZED);
            }
            if (passwordEncoder.matches(authenticationRequest.getPassword(), userEntity.get().getPassword())) {
                String accessToken = createAccessToken(userEntity.get());
                if (accessToken.isEmpty()) {
                    return new ResponseEntity<>("Token not created", HttpStatus.FORBIDDEN);
                }
                SignInResponseDTO signInResponseDTO = new SignInResponseDTO(
                        accessToken,
                        userEntity.get().getUserId(),
                        userEntity.get().getUsername(),
                        LocalDate.now(),
                        LocalTime.now()
                );
                return new ResponseEntity<>(signInResponseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid login credential", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateUser(UserDetailDTO userDetailDTO, long userId) {
        try {
            if (userDetailDTO != null) {
                Optional<UserEntity> userEntity = userRepository.findById(userId);

                userEntity.get().setFirstName(userDetailDTO.getFirstName());
                userEntity.get().setLastName(userDetailDTO.getLastName());
                userEntity.get().setGmail(userDetailDTO.getGmail());
                userEntity.get().setMobile(userDetailDTO.getMobile());
                userEntity.get().setDob(DateConverter.stringToDate(userDetailDTO.getDob()));
                userEntity.get().setProfilePicture(userDetailDTO.getProfilePicture());

                UserEntity user = userRepository.save(userEntity.get());
                if (user != null) {

                    List<UserAddressDetailEntity> byUserEntity = userAddressDetailRepository.findByUserEntity(user);
                    for (UserAddressDetailEntity detailEntity :
                            byUserEntity) {
                        if (detailEntity.getUserAddressEntity().getType().equals(AddressType.USER.toString())) {
                            UserAddressEntity addressDetail = detailEntity.getUserAddressEntity();
                            addressDetail.setAddress(userDetailDTO.getAddress());
                            addressDetail.setDistrict(userDetailDTO.getDistrict());
                            addressDetail.setCity(userDetailDTO.getCity());
                            addressDetail.setPostalCode(userDetailDTO.getPostalCode());
                            addressDetail.setFirstName(user.getFirstName());
                            addressDetail.setLastName(user.getLastName());
                            addressDetail.setMobile(user.getMobile());

                            userAddressRepository.save(addressDetail);
                            detailEntity.setUserEntity(user);
                            detailEntity.setUserAddressEntity(addressDetail);
                            userAddressDetailRepository.save(detailEntity);

                            return new ResponseEntity<>("User Updated", HttpStatus.OK);
                        }
                    }

                    UserAddressEntity userAddress = new UserAddressEntity();
                    userAddress.setAddress(userDetailDTO.getAddress());
                    userAddress.setDistrict(userDetailDTO.getDistrict());
                    userAddress.setCity(userDetailDTO.getCity());
                    userAddress.setPostalCode(userDetailDTO.getPostalCode());
                    userAddress.setFirstName(user.getFirstName());
                    userAddress.setLastName(user.getLastName());
                    userAddress.setMobile(user.getMobile());
                    userAddress.setType(AddressType.USER.toString());

                    userAddressRepository.save(userAddress);
                    UserAddressDetailEntity detailEntity = new UserAddressDetailEntity();
                    detailEntity.setUserEntity(user);
                    detailEntity.setUserAddressEntity(userAddress);
                    userAddressDetailRepository.save(detailEntity);

                    return new ResponseEntity<>("User Updated", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("User not updated", HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                return new ResponseEntity<>("User details not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("User update failed");
        }
    }

    public String createAccessToken(UserEntity userEntity) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole()));
        return JwtGenerator.generateToken(userEntity.getUsername(), Long.toString(userEntity.getUserId()), authorities);
    }
}
