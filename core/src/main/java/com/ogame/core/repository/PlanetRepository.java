package com.ogame.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ogame.core.domain.Planet;
import com.ogame.core.domain.UserUniverse;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
    Optional<List<Planet>> findByUserUniverse(UserUniverse userUniverse);
}