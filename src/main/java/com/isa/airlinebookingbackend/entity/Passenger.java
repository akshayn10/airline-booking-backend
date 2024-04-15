package com.isa.airlinebookingbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data //generates getters and setters
@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String firstName;

    private String lastName;

    private String passportNo;

    private String mealPreference;

    private LocalDate dateOfBirth;

    private String gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "booking_id")
    @JsonIgnore
    private Booking booking;

}