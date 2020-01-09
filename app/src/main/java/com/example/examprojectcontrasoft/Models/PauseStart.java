package com.example.examprojectcontrasoft.Models;


import java.util.Objects;

public class PauseStart {

    private Long checkPauseId;

    private Long staffId;

    private Long breakTimeStart;

    private int day;

    private int month;

    private int year;

    public PauseStart() {
    }

    public PauseStart(Long staffId, Long breakTimeStart, int day, int month, int year) {
        this.staffId = staffId;
        this.breakTimeStart = breakTimeStart;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Long getCheckPauseId() {
        return checkPauseId;
    }

    public void setCheckPauseId(Long checkPauseId) {
        this.checkPauseId = checkPauseId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getBreakTimeStart() {
        return breakTimeStart;
    }

    public void setBreakTimeStart(Long breakTimeStart) {
        this.breakTimeStart = breakTimeStart;
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
        PauseStart that = (PauseStart) o;
        return day == that.day &&
                month == that.month &&
                year == that.year &&
                Objects.equals(checkPauseId, that.checkPauseId) &&
                Objects.equals(staffId, that.staffId) &&
                Objects.equals(breakTimeStart, that.breakTimeStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkPauseId, staffId, breakTimeStart, day, month, year);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PauseStart{");
        sb.append("checkPauseId=").append(checkPauseId);
        sb.append(", staffId=").append(staffId);
        sb.append(", breakTimeStart=").append(breakTimeStart);
        sb.append(", day=").append(day);
        sb.append(", month=").append(month);
        sb.append(", year=").append(year);
        sb.append('}');
        return sb.toString();
    }
}
