package com.example.examprojectcontrasoft.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoggedInUser {
    @SerializedName("staff")
    private Staff staff;

    @SerializedName("companyFunctions")
    private ArrayList<Function> companyFunctions = new ArrayList<>();

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public ArrayList<Function> getCompanyFunctions() {
        return companyFunctions;
    }

    public void setCompanyFunctions(ArrayList<Function> companyFunctions) {
        this.companyFunctions = companyFunctions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggedInUser that = (LoggedInUser) o;
        return Objects.equals(staff, that.staff) &&
                Objects.equals(companyFunctions, that.companyFunctions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staff, companyFunctions);
    }


    @Override
    public String toString() {
        return "LoggedInUser{" +
                "staff=" + staff +
                ", companyFunctions=" + companyFunctions +
                '}';
    }
}
