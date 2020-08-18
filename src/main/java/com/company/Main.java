package com.company;

import com.company.loader.EmployeeLoader;
import com.company.loader.EmployeeLoaderImpl;
import com.company.model.Employee;

import java.io.IOException;
import java.util.List;

public class Main {

    private static EmployeeLoader employeeLoader = new EmployeeLoaderImpl();
    private static final String FILE_PATH = "employees.txt";

    public static void main(String[] args) throws IOException {
        List<Employee> employees = employeeLoader.loadEmployeesFromNet();
        employees.forEach(System.out::println);
        System.out.println("\nNow saving employees");
        employeeLoader.saveEmployeesToFile("employees.txt", employees);
        System.out.println("Employees saved");

        System.out.println("Loading employees from file");
        List<Employee> employeesFromFile = employeeLoader.loadEmployeesFromFile(FILE_PATH);
        employeesFromFile.forEach(System.out::println);

    }
}
