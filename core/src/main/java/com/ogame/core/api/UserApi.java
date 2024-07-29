package com.ogame.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ogame.core.domain.User;
import com.ogame.core.repository.UserRepository;


@RestController
@RequestMapping("/api")
public class UserApi {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserApi(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add("ROLE_USER");
        userRepository.save(user);
        return "User registered successfully";
    }

    @GetMapping("/user")
    public User getUserDetails(@RequestParam String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
    }

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
