package com.example.examprojectcontrasoft.Models;

import java.util.Objects;

public class PauseEnd {

    private Long checkPauseId;

    private Long staffId;

    private Long breakTimeEnd;

    private int day;

    private int month;

    private int year;

    public PauseEnd() {
    }

    public PauseEnd(Long staffId, Long breakTimeEnd, int day, int month, int year) {
        this.staffId = staffId;
        this.breakTimeEnd = breakTimeEnd;
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

    public Long getBreakTimeEnd() {
        return breakTimeEnd;
    }

    public void setBreakTimeEnd(Long breakTimeEnd) {
        this.breakTimeEnd = breakTimeEnd;
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
        PauseEnd pauseEnd = (PauseEnd) o;
        return day == pauseEnd.day &&
                month == pauseEnd.month &&
                year == pauseEnd.year &&
                Objects.equals(checkPauseId, pauseEnd.checkPauseId) &&
                Objects.equals(staffId, pauseEnd.staffId) &&
                Objects.equals(breakTimeEnd, pauseEnd.breakTimeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkPauseId, staffId, breakTimeEnd, day, month, year);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PauseEnd{");
        sb.append("checkPauseId=").append(checkPauseId);
        sb.append(", staffId=").append(staffId);
        sb.append(", breakTimeEnd=").append(breakTimeEnd);
        sb.append(", day=").append(day);
        sb.append(", month=").append(month);
        sb.append(", year=").append(year);
        sb.append('}');
        return sb.toString();
    }
}
