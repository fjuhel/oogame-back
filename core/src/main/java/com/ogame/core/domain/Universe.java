package com.ogame.core.domain;

public enum Universe {
    EAST_BLUE("east-blue"),
    DRESSROSA("dressrosa"),
    WANO("wano");

    private final String urlName;

    Universe(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlName() {
        return urlName;
    }

    public static Universe fromUrlName(String urlName) {
        for (Universe universe : Universe.values()) {
            if (universe.getUrlName().equalsIgnoreCase(urlName)) {
                return universe;
            }
        }
        throw new IllegalArgumentException("Unknown universe: " + urlName);
    }
}
