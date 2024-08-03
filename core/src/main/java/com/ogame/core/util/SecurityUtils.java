package com.ogame.core.util;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.ogame.core.domain.UniverseEnum;
import com.ogame.core.domain.User;
import com.ogame.core.domain.UserUniverse;
import com.ogame.core.repository.UserRepository;
import com.ogame.core.repository.UserUniverseRepository;

@Component
public class SecurityUtils {

    private final UserRepository userRepository;
    private final UserUniverseRepository userUniverseRepository;

    public SecurityUtils(UserRepository userRepository, UserUniverseRepository userUniverseRepository) {
        this.userUniverseRepository = userUniverseRepository;
        this.userRepository = userRepository;
    }

    public User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
    }

    public UserUniverse getUserUniverse(UniverseEnum universe) {
        User user = getAuthenticatedUser();
        return userUniverseRepository.findByUserAndUniverse(user, universe)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "UserUniverse not found"));
    }
}
