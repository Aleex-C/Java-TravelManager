package com.example.model_practic.domain;

import java.util.Objects;

public class Hotel extends Entity<Float>{
    private Float locationId;
    private String hotelName;
    private Integer noRooms;

    @Override
    public String toString() {
        return "Hotel{" +
                "locationId=" + locationId +
                ", hotelName='" + hotelName + '\'' +
                ", noRooms=" + noRooms +
                ", pricePerNight=" + pricePerNight +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(locationId, hotel.locationId) && Objects.equals(hotelName, hotel.hotelName) && Objects.equals(noRooms, hotel.noRooms) && Objects.equals(pricePerNight, hotel.pricePerNight) && type == hotel.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), locationId, hotelName, noRooms, pricePerNight, type);
    }

    public Float getLocationId() {
        return locationId;
    }

    public void setLocationId(Float locationId) {
        this.locationId = locationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(Integer noRooms) {
        this.noRooms = noRooms;
    }

    public Float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public PeopleType getType() {
        return type;
    }

    public void setType(PeopleType type) {
        this.type = type;
    }

    private Float pricePerNight;
    private PeopleType type;

    public Hotel(Float aFloat, Float locationId, String hotelName, Integer noRooms, Float pricePerNight, PeopleType type) {
        super(aFloat);
        this.locationId = locationId;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }
}
