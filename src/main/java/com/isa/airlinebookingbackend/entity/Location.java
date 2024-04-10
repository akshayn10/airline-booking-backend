package com.isa.airlinebookingbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "airport_name", nullable = false)
    private String airportName;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
