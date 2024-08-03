package com.ogame.core.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ogame.core.domain.UniverseConfig;
import com.ogame.core.domain.UniverseEnum;

@Service
public class UniverseService {

    private final Map<UniverseEnum, UniverseConfig> universes = Map.of(
            UniverseEnum.EAST_BLUE, new UniverseConfig(13.5, 10),
            UniverseEnum.DRESSROSA, new UniverseConfig(30, 50),
            UniverseEnum.WANO, new UniverseConfig(1_000, 2_000)
    );

    public double getEconomySpeed(UniverseEnum universe) {
        return universes.get(universe).getEconomySpeed();
    }

    public double getFleetSpeed(UniverseEnum universe) {
        return universes.get(universe).getFleetSpeed();
    }
}
