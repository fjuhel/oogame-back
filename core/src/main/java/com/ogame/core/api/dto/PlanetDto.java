package com.ogame.core.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlanetDto {
    private Long id;
    private String name;

    private int metalMineLevel;
    private int crystalMineLevel;
    private int deuteriumMineLevel;
}