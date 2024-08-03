package com.ogame.core.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogame.core.api.dto.CreateUserPlanetRequest;
import com.ogame.core.api.dto.PlanetDto;
import com.ogame.core.api.dto.UniverseDto;
import com.ogame.core.domain.Planet;
import com.ogame.core.domain.UserUniverse;
import com.ogame.core.repository.PlanetRepository;
import com.ogame.core.service.PlanetMapper;
import com.ogame.core.service.PlanetResourcesService;
import com.ogame.core.util.SecurityUtils;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/user-planet")
public class UserPlanetApi {

    private final PlanetRepository planetRepository;
    private final SecurityUtils securityUtils;
    private final PlanetMapper planetMapper;
    private final PlanetResourcesService planetResourcesService;

    @Autowired
    public UserPlanetApi(PlanetRepository planetRepository, SecurityUtils securityUtils, PlanetMapper planetMapper, PlanetResourcesService planetResourcesService) {
        this.planetRepository = planetRepository;
        this.securityUtils = securityUtils;
        this.planetMapper = planetMapper;
        this.planetResourcesService = planetResourcesService;
    }

    @Operation(tags = "UserPlanet", operationId = "listUserPlanets")
    @PostMapping("/list")
    public List<PlanetDto> listUserPlanets(@RequestBody UniverseDto universe) {
        UserUniverse userUniverse = securityUtils.getUserUniverse(universe.getUniverse());
        return planetRepository.findByUserUniverse(userUniverse)
                .orElse(List.of())
                .stream()
                .map(planet -> planetMapper.toPlanetDto(planet, planetResourcesService))
                .collect(Collectors.toList());
    }

    @Operation(tags = "UserPlanet", operationId = "createUserPlanet")
    @PostMapping("/create")
    public PlanetDto createUserPlanet(@RequestBody CreateUserPlanetRequest createRequest) {
        UserUniverse userUniverse = securityUtils.getUserUniverse(createRequest.getUniverse());
        Planet planet = createRequest.getPlanet();
        planet.setUserUniverse(userUniverse);
        Planet savedPlanet = planetRepository.save(planet);
        return planetMapper.toPlanetDto(savedPlanet, planetResourcesService);
    }
}
