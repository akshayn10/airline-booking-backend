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
@Table(name = "fleet")
public class Fleet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "total_economy_seats")
    private Integer totalEconomySeats;

    @Column(name = "total_premium_seats")
    private Integer totalPremiumSeats;

    @Column(name = "total_business_seats")
    private Integer totalBusinessSeats;
}
