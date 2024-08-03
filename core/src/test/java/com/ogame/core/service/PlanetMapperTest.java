package com.ogame.core.service;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ogame.core.api.dto.PlanetDto;
import com.ogame.core.domain.Planet;
import com.ogame.core.domain.UniverseEnum;
import com.ogame.core.domain.User;
import com.ogame.core.domain.UserUniverse;

@SpringBootTest
public class PlanetMapperTest {

    @Autowired
    private PlanetMapper planetMapper;

    @Autowired
    private PlanetResourcesService planetResourcesService;

    @Test
    public void testToPlanetDto() {
        UserUniverse userUniverse = new UserUniverse(new User(), UniverseEnum.EAST_BLUE);
        Planet planet = new Planet(
          1L, // id
          "Earth", // name
          userUniverse, // userUniverse
          1, // galaxy
          2, // solarSystem
          3, // slot
          5000, // diameter
          0, // minTemperature
          100, // maxTemperature
          180, // baseFields
          50, // metal
          50, // crystal
          50, // deuterium
          LocalDateTime.now(), // lastResourceUpdate
          50, // metalMineLevel
          50, // crystalMineLevel
          50, // deuteriumSynthesizerLevel
          50, // solarPlantLevel
          50, // fusionReactorLevel
          50, // metalStorageLevel
          50, // crystalStorageLevel
          50, // deuteriumTankLevel
          50, // solarSatellite
          50, // crawler
          50, // roboticsFactoryLevel
          50, // shipyardLevel
          50, // researchLabLevel
          50, // allianceDepotLevel
          50, // missileSiloLevel
          50, // naniteFactoryLevel
          50, // terraformerLevel
          50, // spaceDockLevel
          50, // rockerLauncher
          50, // lightLaser
          50, // heavyLaser
          50, // gaussCannon
          50, // ionCannon
          50, // plasmaTurret
          1, // smallShield
          1, // largeShield
          1, // antiBallisticMissile
          1 // interplanetaryMissile
        );

        PlanetDto planetDto = planetMapper.toPlanetDto(planet, planetResourcesService);

        assertEquals(planet.getName(), planetDto.getName());
        // Add more assertions as needed
    }
}
