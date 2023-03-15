package com.example.model_practic.domain;

import java.util.Objects;

public class Location extends Entity<Float>{
    private String locationName;

    @Override
    public String toString() {
        return "Location{" +
                "locationName='" + locationName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Location location = (Location) o;
        return Objects.equals(locationName, location.locationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), locationName);
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Location(Float aFloat, String locationName) {
        super(aFloat);
        this.locationName = locationName;
    }
}
