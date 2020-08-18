package com.company.loader;

import com.company.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeLoaderImpl implements EmployeeLoader {

    private static final String BASE_URL = "https://raw.githubusercontent.com/Lay-Ez/JsonParsing/master/";

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Employee.class, new EmployeeSerializer())
            .create();

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private final JsonGitHubApi jsonGitHubApi = retrofit.create(JsonGitHubApi.class);

    @Override
    public List<Employee> loadEmployeesFromNet() throws IOException {
        Call<List<Employee>> call = jsonGitHubApi.getEmployees();
        return call.execute().body();
    }

    @Override
    public List<Employee> loadEmployeesFromFile(String path) throws IOException {
        try (Scanner scanner = new Scanner(new File(path))){
            String json = scanner.useDelimiter("\\Z").next();
            Type employeeListType = new TypeToken<ArrayList<Employee>>(){}.getType();
            return gson.<ArrayList<Employee>>fromJson(json, employeeListType);
        }
    }

    @Override
    public void saveEmployeesToFile(String path, List<Employee> employees) throws IOException {
        try (PrintStream out = new PrintStream(new File(path))) {
            String json = gson.toJson(employees);
            out.print(json);
        }
    }
}


















