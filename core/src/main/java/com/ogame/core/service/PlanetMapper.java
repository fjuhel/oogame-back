package com.ogame.core.service;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.ogame.core.api.dto.PlanetDto;
import com.ogame.core.domain.Planet;
import com.ogame.core.domain.PlanetResources;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PlanetMapper {
    @Mapping(target = "resources", source = "planet", qualifiedByName = "computePlanetResources")
    @Mapping(target = "totalFields", source = "planet", qualifiedByName = "computeTotalFields")
    @Mapping(target = "occupiedFields", source = "planet", qualifiedByName = "computeOccupiedFields")
    PlanetDto toPlanetDto(Planet planet,  @Context PlanetResourcesService planetResourceService);

    @Mapping(target = "userUniverse", ignore = true)
    Planet toPlanet(PlanetDto planetDto);


    @Named("computePlanetResources")
    default PlanetResources computePlanetResources(Planet planet, @Context PlanetResourcesService planetResourceService) {
        return planetResourceService.computeResourceProduction(planet);
    }

    @Named("computeTotalFields")
    default int computeTotalFields(Planet planet, @Context PlanetResourcesService planetResourceService) {
        return planetResourceService.computeTotalFields(planet);
    }

    @Named("computeOccupiedFields")
    default int computeOccupiedFields(Planet planet, @Context PlanetResourcesService planetResourceService) {
        return planetResourceService.computeOccupiedFields(planet);
    }

}
