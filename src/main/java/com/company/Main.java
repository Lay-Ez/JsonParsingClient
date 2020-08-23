package com.company;

import com.company.loader.*;
import com.company.model.Employee;
import com.company.saver.EmployeeSaver;
import com.company.saver.FileEmployeeSaver;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final String FILE_PATH = "employees.txt";
    private static final String BASE_URL = "https://raw.githubusercontent.com/Lay-Ez/JsonParsing/master/";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Employee.class, new EmployeeSerializer())
            .create();

    private static final JsonGitHubApi jsonGitHubApi = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(JsonGitHubApi.class);

    public static void main(String[] args) throws IOException {
        EmployeeLoader netLoader = new NetEmployeeLoader(jsonGitHubApi);
        EmployeeLoader fileLoader = new FileEmployeeLoader(gson, FILE_PATH);
        EmployeeSaver fileSaver = new FileEmployeeSaver(gson, FILE_PATH);

        List<Employee> employeesFromNet = netLoader.loadEmployees();
        System.out.println("---Employees from net---");
        employeesFromNet.forEach(System.out::println);
        System.out.println("---Saving employees to file---");
        fileSaver.saveEmployees(employeesFromNet);
        List<Employee> employeesFromFile = fileLoader.loadEmployees();
        System.out.println("---Employees from file---");
        employeesFromFile.forEach(System.out::println);
    }
}
