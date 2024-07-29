package com.ogame.core.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ogame.core.domain.Planet;
import com.ogame.core.domain.Universe;
import com.ogame.core.domain.User;
import com.ogame.core.domain.UserUniverse;
import com.ogame.core.repository.PlanetRepository;
import com.ogame.core.repository.UserRepository;
import com.ogame.core.repository.UserUniverseRepository;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUniverseRepository userUniverseRepository;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            User admin = new User();
            admin.setUsername("luffy");
            admin.setPassword(passwordEncoder.encode("nika"));
            admin.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));
            userRepository.save(admin);

            UserUniverse userUniverse1 = new UserUniverse();
            userUniverse1.setUser(admin);
            userUniverse1.setUniverse(Universe.EAST_BLUE);
            userUniverse1.setPoints(1000);
            userUniverse1.setHonorPoints(200);
            userUniverseRepository.save(userUniverse1);

            UserUniverse userUniverse2 = new UserUniverse();
            userUniverse2.setUser(admin);
            userUniverse2.setUniverse(Universe.WANO);
            userUniverse2.setPoints(1500);
            userUniverse2.setHonorPoints(300);
            userUniverseRepository.save(userUniverse2);

            Planet planet1 = new Planet();
            planet1.setName("gum-gum pistol");
            planet1.setUserUniverse(userUniverse1);
            planet1.setMetalMineLevel(10);
            planet1.setCrystalMineLevel(15);
            planet1.setDeuteriumMineLevel(5);
            planetRepository.save(planet1);

            Planet planet2 = new Planet();
            planet2.setName("gum-gum bazooka");
            planet2.setUserUniverse(userUniverse2);
            planet2.setMetalMineLevel(12);
            planet2.setCrystalMineLevel(18);
            planet2.setDeuteriumMineLevel(6);
            planetRepository.save(planet2);
        };
    }
}
