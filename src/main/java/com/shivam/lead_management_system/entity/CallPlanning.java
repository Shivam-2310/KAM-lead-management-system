package com.shivam.lead_management_system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CallPlanning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CallFrequency callFrequency;

    private LocalDate nextCallDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;


    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CallFrequency getCallFrequency() {
        return callFrequency;
    }

    public void setCallFrequency(CallFrequency callFrequency) {
        this.callFrequency = callFrequency;
    }

    public LocalDate getNextCallDate() {
        return nextCallDate;
    }

    public void setNextCallDate(LocalDate nextCallDate) {
        this.nextCallDate = nextCallDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
