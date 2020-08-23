package com.company.loader;

import com.company.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeLoader {
    List<Employee> loadEmployees() throws IOException;
}
