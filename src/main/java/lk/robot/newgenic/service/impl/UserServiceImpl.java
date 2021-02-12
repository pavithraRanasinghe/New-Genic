package lk.robot.newgenic.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lk.robot.newgenic.dto.UserDTO;
import lk.robot.newgenic.dto.request.UserDetailDTO;
import lk.robot.newgenic.dto.request.UserSignUpDTO;
import lk.robot.newgenic.dto.response.SignInResponseDTO;
import lk.robot.newgenic.entity.UserAddressDetailEntity;
import lk.robot.newgenic.entity.UserAddressEntity;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.enums.AddressType;
import lk.robot.newgenic.enums.AuthenticationProvider;
import lk.robot.newgenic.enums.Role;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.jwt.AuthenticationRequest;
import lk.robot.newgenic.jwt.JwtGenerator;
import lk.robot.newgenic.repository.UserAddressDetailRepository;
import lk.robot.newgenic.repository.UserAddressRepository;
import lk.robot.newgenic.repository.UserRepository;
import lk.robot.newgenic.service.UserService;
import lk.robot.newgenic.util.DateConverter;
import lk.robot.newgenic.util.EntityToDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private UserRepository userRepository;
    private UserAddressRepository userAddressRepository;
    private UserAddressDetailRepository userAddressDetailRepository;

    @Autowired
    private AmazonS3 s3Client;
    @Value(value = "application.bucket.name")
    private String bucketName;
    @Value(value = "aws.folder.user")
    private String folder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper,
                           UserRepository userRepository,
                           UserAddressRepository userAddressRepository,
                           UserAddressDetailRepository userAddressDetailRepository) {
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
        this.userAddressDetailRepository = userAddressDetailRepository;
    }

    @Override
    public ResponseEntity<?> signUp(UserSignUpDTO userSignUpDTO, MultipartFile profilePicture) {
        try {
            if (!userSignUpDTO.equals(null)) {

                UserEntity existUser = userRepository.findByGmail(userSignUpDTO.getGmail());
                if (existUser != null){
                    UserEntity userEntity = EntityToDto.userDtoToEntity(userSignUpDTO);
                    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                    userEntity.setRegisteredDate(DateConverter.localDateToSql(LocalDate.now()));
                    userEntity.setRegisteredTime(DateConverter.localTimeToSql(LocalTime.now()));
                    userEntity.setUserUuid(UUID.randomUUID().toString());
                    if (!profilePicture.isEmpty()){
                        uploadFile(profilePicture);
                        userEntity.setProfilePicture(getFileUrl(profilePicture));
                    }
                    UserEntity save = userRepository.save(userEntity);

                    if (save.equals(null)) {
                        return new ResponseEntity<>("User Sign up failed", HttpStatus.BAD_REQUEST);
                    }
                    String accessToken = createAccessToken(userEntity);
                    if (accessToken.isEmpty()) {
                        return new ResponseEntity<>("Token not created", HttpStatus.FORBIDDEN);
                    }
                    SignInResponseDTO signInResponseDTO = new SignInResponseDTO(
                            accessToken,
                            userEntity.getUserUuid(),
                            userEntity.getUsername(),
                            LocalDate.now(),
                            LocalTime.now()
                    );
                    return new ResponseEntity<>(signInResponseDTO, HttpStatus.OK);
                }
                return new ResponseEntity<>("User already signup with "+userSignUpDTO.getGmail(),HttpStatus.BAD_GATEWAY);
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
                        userEntity.get().getUserUuid(),
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
    public ResponseEntity<?> updateUser(UserDetailDTO userDetailDTO, String userId, MultipartFile profilePicture) {
        try {
            if (userDetailDTO != null) {
                UserEntity userEntity = userRepository.findByUserUuid(userId);

                userEntity.setFirstName(userDetailDTO.getFirstName());
                userEntity.setLastName(userDetailDTO.getLastName());
                userEntity.setGmail(userDetailDTO.getGmail());
                userEntity.setMobile(userDetailDTO.getMobile());
                userEntity.setDob(DateConverter.stringToDate(userDetailDTO.getDob()));
                if (!profilePicture.isEmpty()){
                    uploadFile(profilePicture);
                    userEntity.setProfilePicture(getFileUrl(profilePicture));
                }
                UserEntity user = userRepository.save(userEntity);
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

    @Override
    public ResponseEntity<?> getProfile(String userId) {
        try{
            UserEntity userEntity = userRepository.findByUserUuid(userId);
            if (userEntity!= null){
                UserDTO user = modelMapper.map(userEntity, UserDTO.class);
                return new ResponseEntity<>(user,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> newUserFromSuccessHandler(String email, String name, AuthenticationProvider authenticationProvider) {
        try{
            UserEntity userEntity = new UserEntity();
            userEntity.setUserUuid(UUID.randomUUID().toString());
            userEntity.setFirstName(name);
            userEntity.setUsername(name);
            userEntity.setGmail(email);
            userEntity.setAuthenticationProvider(authenticationProvider);
            userEntity.setRole(Role.REGISTERED_USER.toString());

            UserEntity user = userRepository.save(userEntity);
            if (!user.equals(null)){
                String accessToken = createAccessToken(user);
                SignInResponseDTO signInResponseDTO = new SignInResponseDTO(
                        accessToken,
                        user.getUserUuid(),
                        user.getUsername(),
                        LocalDate.now(),
                        LocalTime.now()
                );
                return new ResponseEntity<>(signInResponseDTO,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User not logIn Failed",HttpStatus.BAD_GATEWAY);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateUserFromSuccessHandler(UserEntity userEntity, String email, String name, AuthenticationProvider authenticationProvider) {
        try{
            userEntity.setFirstName(name);
            userEntity.setUsername(name);
            userEntity.setGmail(email);
            userEntity.setAuthenticationProvider(authenticationProvider);
            userEntity.setRole(Role.REGISTERED_USER.toString());

            UserEntity user = userRepository.save(userEntity);
            if (!user.equals(null)){
                String accessToken = createAccessToken(user);
                SignInResponseDTO signInResponseDTO = new SignInResponseDTO(
                        accessToken,
                        user.getUserUuid(),
                        user.getUsername(),
                        LocalDate.now(),
                        LocalTime.now()
                );
                return new ResponseEntity<>(signInResponseDTO,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User not logIn Failed",HttpStatus.BAD_GATEWAY);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void updateResetPasswordToken(String token, String gmail) {
        try{
            UserEntity user = userRepository.findByGmail(gmail);

            if (user != null){
               user.setResetPasswordToken(token);
               userRepository.save(user);
            }else{
                throw new CustomException("User not found");
            }

        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public UserEntity get(String token) {
        try{
            return userRepository.findByResetPasswordToken(token);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void resetPassword(UserEntity userEntity, String password) {
        try{
            String newPassword = passwordEncoder.encode(password);
            userEntity.setPassword(newPassword);

            userRepository.save(userEntity);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    public String createAccessToken(UserEntity userEntity) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole()));
        return JwtGenerator.generateToken(userEntity.getUsername(), userEntity.getUserUuid(), authorities);
    }

    private void uploadFile(MultipartFile multipartFile){
        try {
            File file = new File(multipartFile.getOriginalFilename());

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName ,folder+multipartFile.getOriginalFilename(), file);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(putObjectRequest);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileUrl(MultipartFile profilePicture){
        return s3Client.getUrl(bucketName, folder + profilePicture.getOriginalFilename()).toString();
    }
}
