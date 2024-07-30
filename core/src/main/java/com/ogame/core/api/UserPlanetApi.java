package com.ogame.core.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogame.core.api.dto.PlanetDto;
import com.ogame.core.domain.Planet;
import com.ogame.core.domain.UserUniverse;
import com.ogame.core.repository.PlanetRepository;
import com.ogame.core.service.PlanetMapper;
import com.ogame.core.util.SecurityUtils;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/{universe}/planet")
public class UserPlanetApi {

    private final PlanetRepository planetRepository;
    private final SecurityUtils securityUtils;

    @Autowired
    public UserPlanetApi(PlanetRepository planetRepository, SecurityUtils securityUtils) {
        this.planetRepository = planetRepository;
        this.securityUtils = securityUtils;
    }

    @Operation(tags = "UserPlanet", operationId = "getUserPlanets")
    @GetMapping
    public List<PlanetDto> getUserPlanets(@PathVariable String universe) {
        UserUniverse userUniverse = securityUtils.getUserUniverse(universe);
        return planetRepository.findByUserUniverse(userUniverse)
                .orElse(List.of())
                .stream()
                .map(PlanetMapper::toPlanetDto)
                .collect(Collectors.toList());
    }

    @Operation(tags = "UserPlanet", operationId = "createUserPlanet")
    @PostMapping
    public PlanetDto addPlanet(@PathVariable String universe, @RequestBody Planet planet) {
        UserUniverse userUniverse = securityUtils.getUserUniverse(universe);
        planet.setUserUniverse(userUniverse);
        Planet savedPlanet = planetRepository.save(planet);
        return PlanetMapper.toPlanetDto(savedPlanet);
    }
}
