package com.ogame.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogame.core.domain.User;
import com.ogame.core.repository.UserRepository;
import com.ogame.core.util.SecurityUtils;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final SecurityUtils securityUtils;

    @Autowired
    public UserApi(UserRepository userRepository, PasswordEncoder passwordEncoder, SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Operation(tags = "Users", operationId = "getUser")
    @GetMapping("/")
    public User getUser() {
        return securityUtils.getAuthenticatedUser();
    }
}
