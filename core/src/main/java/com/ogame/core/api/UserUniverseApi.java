package com.ogame.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogame.core.domain.User;
import com.ogame.core.domain.UserUniverse;
import com.ogame.core.repository.UserUniverseRepository;
import com.ogame.core.util.SecurityUtils;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/universe")
public class UserUniverseApi {

    private final UserUniverseRepository userUniverseRepository;
    private final SecurityUtils securityUtils;

    @Autowired
    public UserUniverseApi(UserUniverseRepository userUniverseRepository, SecurityUtils securityUtils) {
        this.userUniverseRepository = userUniverseRepository;
        this.securityUtils = securityUtils;
    }

    @Operation(tags = "UserUniverse", operationId = "createUserUniverse")
    @PostMapping
    public UserUniverse addUserUniverse(@RequestBody UserUniverse data) {
        User user = securityUtils.getAuthenticatedUser();
        data.setUser(user);
        return userUniverseRepository.save(data);
    }
}
