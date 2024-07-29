package com.ogame.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogame.core.domain.User;
import com.ogame.core.repository.UserRepository;
import com.ogame.core.util.SecurityUtils;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtils securityUtils;

    @Autowired
    public UserApi(UserRepository userRepository, PasswordEncoder passwordEncoder, SecurityUtils securityUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityUtils = securityUtils;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add("ROLE_USER");
        userRepository.save(user);
        return "User registered successfully";
    }

    @GetMapping("/details")
    public User getUserDetails() {
        return securityUtils.getAuthenticatedUser();
    }
}
