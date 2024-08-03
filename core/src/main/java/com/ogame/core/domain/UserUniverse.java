package com.ogame.core.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class UserUniverse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NonNull
    @Enumerated(EnumType.STRING)
    private UniverseEnum universe;

    @Setter
    private int points;

    @Setter
    private int honorPoints;

    @Setter
    private int energyTechnologyLevel;

    @Setter
    @OneToMany(mappedBy = "userUniverse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Planet> planets = new HashSet<>();
}
