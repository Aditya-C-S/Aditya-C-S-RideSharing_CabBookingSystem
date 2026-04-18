package com.ridesharing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "place_distance")
public class PlaceDistance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "place_from", nullable = false)
    private String placeFrom;

    @Column(name = "place_to", nullable = false)
    private String placeTo;

    @Column(name = "distance_km", nullable = false)
    private double distanceKm;

    public Long getId() { return id; }
    public String getPlaceFrom() { return placeFrom; }
    public String getPlaceTo() { return placeTo; }
    public double getDistanceKm() { return distanceKm; }
}