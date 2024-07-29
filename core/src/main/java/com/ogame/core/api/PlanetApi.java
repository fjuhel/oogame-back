package com.ogame.core.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogame.core.domain.Planet;
import com.ogame.core.domain.Universe;
import com.ogame.core.domain.UserUniverse;
import com.ogame.core.repository.PlanetRepository;
import com.ogame.core.util.SecurityUtils;

@RestController
@RequestMapping("/api/planet")
public class PlanetApi {

    private final PlanetRepository planetRepository;
    private final SecurityUtils securityUtils;

    @Autowired
    public PlanetApi(PlanetRepository planetRepository, SecurityUtils securityUtils) {
        this.planetRepository = planetRepository;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/{universe}")
    public List<Planet> getUserPlanets(@PathVariable Universe universe) {
        UserUniverse userUniverse = securityUtils.getUserUniverse(universe);

        return planetRepository.findByUserUniverse(userUniverse).orElse(List.of());
    }
    @PostMapping("/{universe}")
    public Planet addPlanet(@PathVariable Universe universe, @RequestBody Planet planet) {
        UserUniverse userUniverse = securityUtils.getUserUniverse(universe);
        planet.setUserUniverse(userUniverse);
        return planetRepository.save(planet);
    }


}
