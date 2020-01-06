package com.example.examprojectcontrasoft.Models;

import java.util.Objects;

public class Function {

    private Long functionId;

    private String functionName;

    private boolean allowed;

    private Company company;

    public Function(String functionName, boolean allowed, Company company) {
        this.functionName = functionName;
        this.allowed = allowed;
        this.company = company;
    }

    public Function() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return allowed == function.allowed &&
                Objects.equals(functionId, function.functionId) &&
                Objects.equals(functionName, function.functionName) &&
                Objects.equals(company, function.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(functionId, functionName, allowed, company);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Function{");
        sb.append("functionId=").append(functionId);
        sb.append(", functionName='").append(functionName).append('\'');
        sb.append(", allowed=").append(allowed);
        sb.append(", company=").append(company);
        sb.append('}');
        return sb.toString();
    }
}

