package com.company.saver;

import com.company.model.Employee;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class FileEmployeeSaver implements EmployeeSaver {

    private final Gson gson;
    private final String path;

    public FileEmployeeSaver(Gson gson, String path) {
        this.gson = gson;
        this.path = path;
    }

    @Override
    public void saveEmployees(List<Employee> employees) throws IOException {
        try (PrintStream out = new PrintStream(new File(path))) {
            String json = gson.toJson(employees);
            out.print(json);
        }
    }
}
