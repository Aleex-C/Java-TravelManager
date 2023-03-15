package com.example.model_practic.domain;

import java.sql.Date;
import java.util.Objects;

public class Wrapper {
    private String hotel;
    private String location;
    private Date startD;
    private Date endD;
    private Integer percents;

    public Wrapper(String hotel, String location, Date startD, Date endD, Integer percents) {
        this.hotel = hotel;
        this.location = location;
        this.startD = startD;
        this.endD = endD;
        this.percents = percents;
    }

    public Integer getPercents() {
        return percents;
    }

    public void setPercents(Integer percents) {
        this.percents = percents;
    }

    public Wrapper(String hotel, String location, Date startD, Date endD) {
        this.hotel = hotel;
        this.location = location;
        this.startD = startD;
        this.endD = endD;
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "hotel='" + hotel + '\'' +
                ", location='" + location + '\'' +
                ", startD=" + startD +
                ", endD=" + endD +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wrapper wrapper = (Wrapper) o;
        return Objects.equals(hotel, wrapper.hotel) && Objects.equals(location, wrapper.location) && Objects.equals(startD, wrapper.startD) && Objects.equals(endD, wrapper.endD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotel, location, startD, endD);
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartD() {
        return startD;
    }

    public void setStartD(Date startD) {
        this.startD = startD;
    }

    public Date getEndD() {
        return endD;
    }

    public void setEndD(Date endD) {
        this.endD = endD;
    }
}
