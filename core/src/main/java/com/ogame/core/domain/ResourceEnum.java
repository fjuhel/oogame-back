package com.ogame.core.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum ResourceEnum {
  METAL,
  CRYSTAL,
  DEUTERIUM,
  ENERGY
}
