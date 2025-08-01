package com.springLearning.jpaLearning.security;

import com.springLearning.jpaLearning.dto.LoginRequestDto;
import com.springLearning.jpaLearning.dto.LoginResponseDto;
import com.springLearning.jpaLearning.dto.SignupResponseDto;
import com.springLearning.jpaLearning.entity.User;
import com.springLearning.jpaLearning.entity.type.AuthProviderType;
import com.springLearning.jpaLearning.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;




    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }

    public User signUpInternal(LoginRequestDto signupRequestDto,AuthProviderType authProviderType,String providerId){
        User user =userRepository.findByUsername(signupRequestDto.getUsername() ).orElse(null);
        if(user!=null) throw new IllegalArgumentException("User Already Exists");
        user =  User.builder()
                .username(signupRequestDto.getUsername())
                .providerId(providerId)
                .providerType(authProviderType)
                .build();
        if(authProviderType == AuthProviderType.EMAIL){
            user.setPassword((passwordEncoder.encode(signupRequestDto.getPassword())));
        }

        return userRepository.save(user);

    }

//    Login Controller
    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        User user = signUpInternal(signupRequestDto,AuthProviderType.EMAIL,null);
        return new SignupResponseDto(user.getId(), user.getUsername());
    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User user, String registrationId) {
//        Fetch ProviderType and ProviderId
//        Save the providerType and ProviderId info with user
//        If the user has an acc : directly login the account
//        Else signup and login
        AuthProviderType providerType = authUtil.getProviderTypeFromregistrationId(registrationId);

        String providerId = authUtil.determineProviderIdFromOAuth2User(user,registrationId);

        User newUser = userRepository.findByProviderIdAndProviderType(providerId,providerType).orElse(null);

        String email = user.getAttribute("email");

        User emailUser = userRepository.findByUsername(email).orElse(null);

        if(newUser ==  null && emailUser==null) {
//            Signup Flow Entry cause User doesnt exist
            String username = authUtil.determineUsernameFromOAuth2User(user,registrationId,providerId);

            newUser = (User) signUpInternal(new LoginRequestDto(username,null),providerType,providerId);

        }else if(user!=null){
            if(email!=null && !email.isBlank() && !email.equals(newUser.getUsername())){
                newUser.setUsername(email);
                userRepository.save(newUser);
            };
        }else {
            throw new BadCredentialsException("This email is already registered with email "+ email);
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(newUser), newUser.getId() );

        return ResponseEntity.ok(loginResponseDto);
    }
}
