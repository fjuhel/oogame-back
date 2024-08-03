package com.ogame.core.api.dto;

import java.time.LocalDateTime;

import com.ogame.core.domain.PlanetResources;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanetDto {

    // Planet info
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private int diameter;
    @NotNull
    private int minTemperature;
    @NotNull
    private int maxTemperature;

    // Position
    @NotNull
    private int galaxy;
    @NotNull
    private int solarSystem;
    @NotNull
    private int slot;

    // Resource levels
    @NotNull
    private int metal;
    @NotNull
    private int crystal;
    @NotNull
    private int deuterium;
    @NotNull
    private LocalDateTime lastResourceUpdate;

    // Resource prodution and storage
    @NotNull
    private PlanetResources resources;


    // Resources
    @NotNull
    private int metalMineLevel;
    @NotNull
    private int crystalMineLevel;
    @NotNull
    private int deuteriumMineLevel;
    @NotNull
    private int solarPlantLevel;
    @NotNull
    private int fusionReactorLevel;
    @NotNull
    private int metalStorageLevel;
    @NotNull
    private int crystalStorageLevel;
    @NotNull
    private int deuteriumStorageLevel;
    @NotNull
    private int solarSatellite;
    @NotNull
    private int crawler;

    // Facilities
    @NotNull
    private int roboticsFactoryLevel;
    @NotNull
    private int shipyardLevel;
    @NotNull
    private int researchLabLevel;
    @NotNull
    private int allianceDepotLevel;
    @NotNull
    private int missileSiloLevel;
    @NotNull
    private int naniteFactoryLevel;
    @NotNull
    private int terraformerLevel;
    @NotNull
    private int spaceDockLevel;

    // Defence
    @NotNull
    private int rocketLauncher;
    @NotNull
    private int lightLaser;
    @NotNull
    private int heavyLaser;
    @NotNull
    private int gaussCannon;
    @NotNull
    private int ionCannon;
    @NotNull
    private int plasmaTurret;
    @NotNull
    private int smallShield;
    @NotNull
    private int largeShield;
    @NotNull
    private int antiBallisticMissile;
    @NotNull
    private int interplanetaryMissile;
}
