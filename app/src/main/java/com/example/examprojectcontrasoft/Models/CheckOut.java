package com.example.examprojectcontrasoft.Models;

import java.util.Objects;

public class CheckOut {

    private Long checkOutId;

    private Long staffId;

    private Long epochCheckOutTime;

    private int day;

    private int month;

    private int year;

    public CheckOut(Long staffId, Long epochCheckOutTime, int day, int month, int year) {
        this.staffId = staffId;
        this.epochCheckOutTime = epochCheckOutTime;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public CheckOut() {
    }

    public Long getCheckOutId() {
        return checkOutId;
    }

    public void setCheckOutId(Long checkOutId) {
        this.checkOutId = checkOutId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getEpochCheckOutTime() {
        return epochCheckOutTime;
    }

    public void setEpochCheckOutTime(Long epochCheckOutTime) {
        this.epochCheckOutTime = epochCheckOutTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckOut checkOut = (CheckOut) o;
        return day == checkOut.day &&
                month == checkOut.month &&
                year == checkOut.year &&
                Objects.equals(checkOutId, checkOut.checkOutId) &&
                Objects.equals(staffId, checkOut.staffId) &&
                Objects.equals(epochCheckOutTime, checkOut.epochCheckOutTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkOutId, staffId, epochCheckOutTime, day, month, year);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckOut{");
        sb.append("checkOutId=").append(checkOutId);
        sb.append(", staffId=").append(staffId);
        sb.append(", epochCheckOutTime=").append(epochCheckOutTime);
        sb.append(", day=").append(day);
        sb.append(", month=").append(month);
        sb.append(", year=").append(year);
        sb.append('}');
        return sb.toString();
    }
}

