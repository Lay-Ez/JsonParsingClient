package com.company.model;

public class Employee {

    private final static double AVG_WORKING_DAYS_PER_MONTH = 20.8;
    private final static double AVG_WORKING_HOURS_PER_DAY = 8;

    private final EmployeeType type;
    private final String id;
    private final String name;
    private final double rate;

    public Employee(EmployeeType type, String id, String name, double rate) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public double getAvgMonthlySalary() {

        switch (type) {

            case HOURLY:
                return AVG_WORKING_DAYS_PER_MONTH * AVG_WORKING_HOURS_PER_DAY * rate;

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
