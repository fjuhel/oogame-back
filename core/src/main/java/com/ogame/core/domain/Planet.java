package com.ogame.core.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_universe_id")
    @NonNull
    private UserUniverse userUniverse;

    // Position
    private int galaxy;
    private int solarSystem;
    private int slot;

    // Planet info
    private int diameter;
    private int minTemperature;
    private int maxTemperature;

    // Resource levels
    private int metal;
    private int crystal;
    private int deuterium;
    private LocalDateTime  lastResourceUpdate = LocalDateTime.now();

    // Resources
    private int metalMineLevel;
    private int crystalMineLevel;
    private int deuteriumMineLevel;
    private int solarPlantLevel;
    private int fusionReactorLevel;
    private int metalStorageLevel;
    private int crystalStorageLevel;
    private int deuteriumStorageLevel;
    private int solarSatellite;
    private int crawler;

    // Facilities
    private int roboticsFactoryLevel;
    private int shipyardLevel;
    private int researchLabLevel;
    private int allianceDepotLevel;
    private int missileSiloLevel;
    private int naniteFactoryLevel;
    private int terraformerLevel;
    private int spaceDockLevel;

    // Defence
    private int rocketLauncher;
    private int lightLaser;
    private int heavyLaser;
    private int gaussCannon;
    private int ionCannon;
    private int plasmaTurret;
    private int smallShield;
    private int largeShield;
    private int antiBallisticMissile;
    private int interplanetaryMissile;
}
