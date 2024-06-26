package com.isa.airlinebookingbackend.entity;

import com.isa.airlinebookingbackend.entity.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bookingId;

    @CreationTimestamp
    private Instant bookingDate;

    private float totalCost;

    private int noOfSeatBooked;

    private String seatTypeBooked;

    @ManyToOne
    private Flight flight;

    private boolean isCancelled = false;
    private boolean isPaymentCompleted = false;

    @ManyToOne
    private User user;

    private int[] seatNumbers;

    @OneToMany( fetch = FetchType.EAGER)
    private List<Passenger> passengers;

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingDate=" + bookingDate +
                ", isCancelled=" + isCancelled +
                ", totalCost=" + totalCost +
                ", noOfSeatBooked=" + noOfSeatBooked +
                ", seatTypeBooked='" + seatTypeBooked + '\'' +
                ", flightId=" + flight.getId() +
                ", user=" + user.getId() +
                ", seatNumbers=" + seatNumbers +
                '}';
    }
}
