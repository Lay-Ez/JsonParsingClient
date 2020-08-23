package com.company.loader;

import com.company.model.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileEmployeeLoader implements EmployeeLoader {

    private final Gson gson;
    private final String path;

    public FileEmployeeLoader(Gson gson, String path) {
        this.gson = gson;
        this.path = path;
    }

    @Override
    public List<Employee> loadEmployees() throws IOException {
        try (Scanner scanner = new Scanner(new File(path))){
            String json = scanner.useDelimiter("\\Z").next();
            Type employeeListType = new TypeToken<ArrayList<Employee>>(){}.getType();
            return gson.<ArrayList<Employee>>fromJson(json, employeeListType);
        }
    }
}
