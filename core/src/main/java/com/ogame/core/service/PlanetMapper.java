package com.ogame.core.service;

import com.ogame.core.api.dto.PlanetDto;
import com.ogame.core.domain.Planet;

public class PlanetMapper {
    public static PlanetDto toPlanetDto(Planet planet) {
        return new PlanetDto(
            planet.getId(),
            planet.getName(),
            planet.getMetalMineLevel(),
            planet.getCrystalMineLevel(),
            planet.getDeuteriumMineLevel()
        );
    }
}
