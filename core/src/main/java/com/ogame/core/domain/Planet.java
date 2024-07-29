package com.ogame.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_universe_id")
    private UserUniverse userUniverse;

    private int metalMineLevel;
    private int crystalMineLevel;
    private int deuteriumMineLevel;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserUniverse getUserUniverse() {
        return userUniverse;
    }

    public void setUserUniverse(UserUniverse userUniverse) {
        this.userUniverse = userUniverse;
    }

    public int getMetalMineLevel() {
        return metalMineLevel;
    }

    public void setMetalMineLevel(int metalMineLevel) {
        this.metalMineLevel = metalMineLevel;
    }

    public int getCrystalMineLevel() {
        return crystalMineLevel;
    }

    public void setCrystalMineLevel(int crystalMineLevel) {
        this.crystalMineLevel = crystalMineLevel;
    }

    public int getDeuteriumMineLevel() {
        return deuteriumMineLevel;
    }

    public void setDeuteriumMineLevel(int deuteriumMineLevel) {
        this.deuteriumMineLevel = deuteriumMineLevel;
    }
}
