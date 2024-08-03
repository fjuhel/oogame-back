package com.ogame.core.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PlanetResources {

  @NotNull
  public int metalProduction;

  @NotNull
  public int crystalProduction;

  @NotNull
  public int deuteriumProduction;

  @NotNull
  public int energyProduction;

  @NotNull
  public int energyConsumption;

  @NotNull
  public int metalStorage;

  @NotNull
  public int crystalStorage;

  @NotNull
  public int deuteriumStorage;

}