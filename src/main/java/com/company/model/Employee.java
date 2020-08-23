package com.company.model;

public class Employee {

    private EmployeeType type;
    private String id;
    private String name;
    private double rate;

    public Employee(EmployeeType type, String id, String name, double rate) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public double getAvgMonthlySalary() {

        switch (type) {

            case HOURLY:
                return 20.8 * 8 * rate;

            case MONTHLY:
                return rate;

            default:
                return -1;
        }
    }

    public EmployeeType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "type=" + type + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rate=" + rate + '\'' +
                ", avgMonthly=" + getAvgMonthlySalary() + '\'' +
                '}';
    }
}
