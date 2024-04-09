package com.isa.airlinebookingbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_location_id")
    private Location departureLocation;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_location_id")
    private Location arrivalLocation;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fleet_id")
    private Fleet fleet;

    @Column(name = "departure_time", nullable = false)
    private OffsetDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private OffsetDateTime arrivalTime;

    @Column(name = "remaining_economy_seats")
    private Integer remainingEconomySeats;

    @Column(name = "remaining_premium_seats")
    private Integer remainingPremiumSeats;

    @Column(name = "remaining_business_seats")
    private Integer remainingBusinessSeats;

    @Column(name = "economy_seat_fare")
    private Float economySeatFare;

    @Column(name = "premium_seat_fare")
    private Float premiumSeatFare;

    @Column(name = "business_seat_fare")
    private Float businessSeatFare;

    @Column(name = "flight_cancelled", nullable = false)
    private Boolean flightCancelled = false;
}
