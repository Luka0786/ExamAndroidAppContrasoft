package com.example.examprojectcontrasoft.Models;

import java.util.Objects;

public class WorkedDay {

    private String date;

    private String workTime;

    private String pauseTotalTime;

    private String totalWorked;

    private String totalTimeAtWork;

    public WorkedDay(String date, String workTime, String pauseTotalTime, String totalWorked, String totalTimeAtWork) {
        this.date = date;
        this.workTime = workTime;
        this.pauseTotalTime = pauseTotalTime;
        this.totalWorked = totalWorked;
        this.totalTimeAtWork = totalTimeAtWork;
    }

    public WorkedDay() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPauseTotalTime() {
        return pauseTotalTime;
    }

    public void setPauseTotalTime(String pauseTotalTime) {
        this.pauseTotalTime = pauseTotalTime;
    }

    public String getTotalWorked() {
        return totalWorked;
    }

    public void setTotalWorked(String totalWorked) {
        this.totalWorked = totalWorked;
    }

    public String getTotalTimeAtWork() {
        return totalTimeAtWork;
    }

    public void setTotalTimeAtWork(String totalTimeAtWork) {
        this.totalTimeAtWork = totalTimeAtWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkedDay that = (WorkedDay) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(workTime, that.workTime) &&
                Objects.equals(pauseTotalTime, that.pauseTotalTime) &&
                Objects.equals(totalWorked, that.totalWorked) &&
                Objects.equals(totalTimeAtWork, that.totalTimeAtWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, workTime, pauseTotalTime, totalWorked, totalTimeAtWork);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WorkedDay{");
        sb.append("date='").append(date).append('\'');
        sb.append(", workTime='").append(workTime).append('\'');
        sb.append(", pauseTotalTime='").append(pauseTotalTime).append('\'');
        sb.append(", totalWorked='").append(totalWorked).append('\'');
        sb.append(", totalTimeAtWork='").append(totalTimeAtWork).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
