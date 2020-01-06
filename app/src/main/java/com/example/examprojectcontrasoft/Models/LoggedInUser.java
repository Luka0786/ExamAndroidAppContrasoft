package com.example.examprojectcontrasoft.Models;

import java.util.List;
import java.util.Objects;

public class LoggedInUser {

    private Staff staff;

    private List<Function> companyFunctions;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<Function> getCompanyFunctions() {
        return companyFunctions;
    }

    public void setCompanyFunctions(List<Function> companyFunctions) {
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
        final StringBuilder sb = new StringBuilder("LoggedInUser{");
        sb.append("staff=").append(staff);
        sb.append(", companyFunctions=").append(companyFunctions);
        sb.append('}');
        return sb.toString();
    }
}
