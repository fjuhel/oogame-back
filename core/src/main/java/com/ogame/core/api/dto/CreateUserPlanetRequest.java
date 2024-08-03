package com.ogame.core.api.dto;

import com.ogame.core.domain.Planet;
import com.ogame.core.domain.UniverseEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserPlanetRequest {
  private UniverseEnum universe;

  private Planet planet;
}
