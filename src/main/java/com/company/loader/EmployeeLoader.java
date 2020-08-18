package com.company.loader;

import com.company.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeLoader {
    List<Employee> loadEmployeesFromNet() throws IOException;
    List<Employee> loadEmployeesFromFile(String path) throws IOException;
    void saveEmployeesToFile(String path, List<Employee> employees) throws IOException;
}
