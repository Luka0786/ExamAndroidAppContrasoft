package com.example.examprojectcontrasoft.Models;


import java.util.Objects;

public class CheckIn {

    private Long checkInId;

    private Long staffId;

    private Long epochCheckInTime;

    private int day;

    private int month;

    private int year;

    public CheckIn(Long staffId, Long epochCheckInTime, int day, int month, int year) {
        this.staffId = staffId;
        this.epochCheckInTime = epochCheckInTime;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public CheckIn() {
    }

    public Long getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(Long checkInId) {
        this.checkInId = checkInId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getEpochCheckInTime() {
        return epochCheckInTime;
    }

    public void setEpochCheckInTime(Long epochCheckInTime) {
        this.epochCheckInTime = epochCheckInTime;
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
        CheckIn checkIn = (CheckIn) o;
        return day == checkIn.day &&
                month == checkIn.month &&
                year == checkIn.year &&
                Objects.equals(checkInId, checkIn.checkInId) &&
                Objects.equals(staffId, checkIn.staffId) &&
                Objects.equals(epochCheckInTime, checkIn.epochCheckInTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkInId, staffId, epochCheckInTime, day, month, year);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckIn{");
        sb.append("checkInId=").append(checkInId);
        sb.append(", staffId=").append(staffId);
        sb.append(", epochCheckInTime=").append(epochCheckInTime);
        sb.append(", day=").append(day);
        sb.append(", month=").append(month);
        sb.append(", year=").append(year);
        sb.append('}');
        return sb.toString();
    }
}

