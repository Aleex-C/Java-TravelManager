package com.example.model_practic.domain;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.Objects;

public class SpecialOffer extends Entity<Float>{
    private Float hotelId;
    private Date startDate;
    private Date endDate;
    private Integer percents;

    public SpecialOffer(Float aFloat, Float hotelId, Date startDate, Date endDate, Integer percents) {
        super(aFloat);
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percents = percents;
    }

    @Override
    public String toString() {
        return "SpecialOffer{" +
                "hotelId=" + hotelId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", percents=" + percents +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpecialOffer that = (SpecialOffer) o;
        return percents == that.percents && Objects.equals(hotelId, that.hotelId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hotelId, startDate, endDate, percents);
    }

    public Float getHotelId() {
        return hotelId;
    }

    public void setHotelId(Float hotelId) {
        this.hotelId = hotelId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPercents() {
        return percents;
    }

    public void setPercents(Integer percents) {
        this.percents = percents;
    }
}
