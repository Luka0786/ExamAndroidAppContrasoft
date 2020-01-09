package com.example.examprojectcontrasoft.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkedDaysTwoDates {

    private ArrayList<WorkedDay> workedDayDTOList = new ArrayList<>();

    public WorkedDaysTwoDates(ArrayList workedDayDTOList) {
        this.workedDayDTOList = workedDayDTOList;
    }

    public WorkedDaysTwoDates() {
    }

    public ArrayList<WorkedDay> getWorkedDayDTOList() {
        return workedDayDTOList;
    }

    public void setWorkedDayDTOList(ArrayList workedDayDTOList) {
        this.workedDayDTOList = workedDayDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkedDaysTwoDates that = (WorkedDaysTwoDates) o;
        return Objects.equals(workedDayDTOList, that.workedDayDTOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workedDayDTOList);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WorkedDaysTwoDates{");
        sb.append("workedDayDTOList=").append(workedDayDTOList);
        sb.append('}');
        return sb.toString();
    }
}
