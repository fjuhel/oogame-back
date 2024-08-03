package com.ogame.core.config;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ogame.core.domain.Planet;
import com.ogame.core.domain.UniverseEnum;
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
            User admin = new User("luffy", passwordEncoder.encode("nika"));
            admin.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));
            userRepository.save(admin);

            UserUniverse userUniverse1 = new UserUniverse(admin, UniverseEnum.EAST_BLUE);
            userUniverse1.setPoints(1000);
            userUniverse1.setHonorPoints(200);
            userUniverseRepository.save(userUniverse1);

            UserUniverse userUniverse2 = new UserUniverse(admin, UniverseEnum.WANO);
            userUniverse2.setPoints(1500);
            userUniverse2.setHonorPoints(300);
            userUniverseRepository.save(userUniverse2);

            Planet planet1_u1 = new Planet(
                1L,
                "gum-gum pistol",
                userUniverse1,
                5,
                250,
                12,
                5000,
                0,
                100,
                180,
                50,
                50,
                50,
                LocalDateTime.now(),
                20,
                15,
                10,
                21,
                3,
                5,
                5,
                2,
                50,
                0,
                10,
                12,
                12,
                0,
                3,
                1,
                2,
                0,
                50,
                50,
                50,
                50,
                50,
                50,
                1,
                1,
                1,
                1
            );
            planetRepository.save(planet1_u1);

            Planet planet2_u1 = new Planet("gum-gum bazooka", userUniverse1);
            planet2_u1.setMetalMineLevel(12);
            planet2_u1.setCrystalMineLevel(18);
            planet2_u1.setDeuteriumMineLevel(6);
            planet2_u1.setSolarPlantLevel(20);
            planetRepository.save(planet2_u1);

            Planet planet1_u2 = new Planet("gum-gum red hawk", userUniverse2);
            planet1_u2.setMetalMineLevel(12);
            planet1_u2.setCrystalMineLevel(18);
            planet1_u2.setDeuteriumMineLevel(15);
            planet1_u2.setSolarPlantLevel(25);
            planetRepository.save(planet1_u2);
        };
    }
}
