package com.ogame.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ogame.core.domain.UniverseEnum;
import com.ogame.core.domain.User;
import com.ogame.core.domain.UserUniverse;

public interface UserUniverseRepository extends JpaRepository<UserUniverse, Long> {
    Optional<UserUniverse> findByUserAndUniverse(User user, UniverseEnum universe);
}
