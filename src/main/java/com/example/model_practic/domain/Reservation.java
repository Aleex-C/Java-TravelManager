package com.example.model_practic.domain;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public class Reservation extends Entity<Float>{
    private Float clientId;
    private Float hotelId;
    private LocalDateTime startDate;
    private Integer noNights;

    public Reservation(Float aFloat, Float clientId, Float hotelId, LocalDateTime startDate, Integer noNights) {
        super(aFloat);
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "clientId=" + clientId +
                ", hotelId=" + hotelId +
                ", startDate=" + startDate +
                ", noNights=" + noNights +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(hotelId, that.hotelId) && Objects.equals(startDate, that.startDate) && Objects.equals(noNights, that.noNights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientId, hotelId, startDate, noNights);
    }

    public Float getClientId() {
        return clientId;
    }

    public void setClientId(Float clientId) {
        this.clientId = clientId;
    }

    public Float getHotelId() {
        return hotelId;
    }

    public void setHotelId(Float hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getNoNights() {
        return noNights;
    }

    public void setNoNights(Integer noNights) {
        this.noNights = noNights;
    }
}
