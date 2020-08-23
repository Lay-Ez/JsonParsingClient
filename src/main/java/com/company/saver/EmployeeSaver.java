package com.company.saver;

import com.company.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeSaver {
    void saveEmployees(List<Employee> employees) throws IOException;
}
