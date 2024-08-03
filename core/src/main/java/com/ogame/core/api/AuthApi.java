package com.ogame.core.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogame.core.config.JwtTokenProvider;
import com.ogame.core.domain.User;
import com.ogame.core.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {

    private static final Logger logger = LoggerFactory.getLogger(AuthApi.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(tags = "Auth", operationId = "login")
    @PostMapping("/login")
    public Map<String, String> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException | AuthenticationServiceException | DisabledException | LockedException e) {
            logger.error("Authentication failed for username: {}", loginRequest.getUsername(), e);
            throw e;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return response;
    }

    @Operation(tags = "Auth", operationId = "register")
    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
        user.getRoles().add("ROLE_USER");
        return userRepository.save(user);
    }

    @Setter
    @Getter
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Setter
    @Getter
    public static class RegisterRequest {
        private String username;
        private String password;
    }
}
