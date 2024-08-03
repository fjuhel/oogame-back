package com.ogame.core.service;

import org.springframework.stereotype.Service;

import com.ogame.core.domain.Planet;
import com.ogame.core.domain.PlanetResources;


@Service
public class PlanetResourcesService {

    private final UniverseService universeService;

    public PlanetResourcesService(UniverseService universeService) {
        this.universeService = universeService;
    }

    public PlanetResources computeResourceProduction(Planet planet) {

        double economySpeed = universeService.getEconomySpeed(planet.getUserUniverse().getUniverse());

        return new PlanetResources(
                computeMetalProduction(planet, economySpeed),
                computeCrystalProduction(planet, economySpeed),
                computeDeuteriumProduction(planet, economySpeed),
                computeEnergyProduction(planet),
                computeEnergyConsumption(planet),
                computeMetalStorage(planet),
                computeCrystalStorage(planet),
                computeDeuteriumStorage(planet)
        );
    }

    public int computeMetalProduction(Planet planet, double economySpeed) {
        int metalMineLevel = planet.getMetalMineLevel();
        return (int) Math.round(30 * metalMineLevel * Math.pow(1.1, metalMineLevel) * economySpeed);
    }

    private int computeCrystalProduction(Planet planet, double economySpeed) {
        int crystalMineLevel = planet.getCrystalMineLevel();
        return (int) Math.round(20 * crystalMineLevel * Math.pow(1.1, crystalMineLevel) * economySpeed);
    }

    private int computeDeuteriumProduction(Planet planet, double economySpeed) {
        int deuteriumMineLevel = planet.getDeuteriumMineLevel();
        return (int) Math.round(10 * deuteriumMineLevel * Math.pow(1.1, deuteriumMineLevel) * (-0.002 * planet.getMaxTemperature() + 1.28) * economySpeed);
    }

    public int computeEnergyProduction(Planet planet) {
      int solarPlantLevel = planet.getSolarPlantLevel();
      double solarPlantProduction = 20 * solarPlantLevel * Math.pow(1.1, solarPlantLevel);

      int fusionReactorLevel = planet.getFusionReactorLevel();
      double fusionReactorProduction = 30 * fusionReactorLevel * Math.pow(1.05 + planet.getUserUniverse().getEnergyTechnologyLevel() * 0.01, fusionReactorLevel);

      int solarSatellite = planet.getSolarSatellite();
      double solarSatelliteProduction = solarSatellite * (planet.getMaxTemperature() / 4.0 + 20);
      return (int) Math.round(solarPlantProduction + fusionReactorProduction + solarSatelliteProduction);
    }

    public int computeEnergyConsumption(Planet planet) {
        int metalMineLevel = planet.getMetalMineLevel();
        int crystalMineLevel = planet.getCrystalMineLevel();
        int deuteriumMineLevel = planet.getDeuteriumMineLevel();

        double metalMineConsumption = 10 * metalMineLevel * Math.pow(1.1, metalMineLevel);
        double crystalMineConsumption = 10 * crystalMineLevel * Math.pow(1.1, crystalMineLevel);
        double deuteriumMineConsumption = 20 * deuteriumMineLevel * Math.pow(1.1, deuteriumMineLevel);

        return (int) Math.round(metalMineConsumption + crystalMineConsumption + deuteriumMineConsumption);
    }

    public int computeMetalStorage(Planet planet) {
        int metalStorageLevel  = planet.getMetalStorageLevel();
        return computeStorage(metalStorageLevel);
    }

    public int computeCrystalStorage(Planet planet) {
        int crystalStorageLevel  = planet.getCrystalStorageLevel();
        return computeStorage(crystalStorageLevel);
    }

    public int computeDeuteriumStorage(Planet planet) {
        int deuteriumStorageLevel  = planet.getDeuteriumStorageLevel();
        return computeStorage(deuteriumStorageLevel);
    }

    private int computeStorage(int storageLevel) {
        if (storageLevel == 0) return 10_000;
        return (int) (5000 * Math.floor(2.5 * Math.pow(Math.E, (20 / 33) * storageLevel)));
    }

    public int computeTotalFields(Planet planet) {
        int baseFields = planet.getBaseFields();
        int terraformerLevel = planet.getTerraformerLevel();
        int terraformedFields = terraformerLevel * 5;
        int terraformedFieldsBonusFields = (terraformerLevel / 2);
        return baseFields + terraformedFields + terraformedFieldsBonusFields;
    }

    public int computeOccupiedFields(Planet planet) {
        return
            planet.getMetalMineLevel()
            + planet.getCrystalMineLevel()
            + planet.getDeuteriumMineLevel()
            + planet.getSolarPlantLevel()
            + planet.getFusionReactorLevel()
            + planet.getSolarSatellite()
            + planet.getMetalStorageLevel()
            + planet.getCrystalStorageLevel()
            + planet.getDeuteriumStorageLevel()
            + planet.getRoboticsFactoryLevel()
            + planet.getShipyardLevel()
            + planet.getResearchLabLevel()
            + planet.getAllianceDepotLevel()
            + planet.getMissileSiloLevel()
            + planet.getNaniteFactoryLevel()
            + planet.getTerraformerLevel();
    }
}
