package com.company.loader;

import com.company.model.Employee;
import com.google.gson.Gson;
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

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Lay-Ez/JsonParsing/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final JsonGitHubApi jsonGitHubApi = retrofit.create(JsonGitHubApi.class);
    private final Gson gson = new Gson();

    @Override
    public List<Employee> loadEmployeesFromNet() throws IOException {
        Call<List<Employee>> call = jsonGitHubApi.getEmployees();
        return call.execute().body();
    }

    @Override
    public List<Employee> loadEmployeesFromFile(String path) throws IOException {
        Scanner scanner = new Scanner(new File("employees.txt"));
        String json = scanner.useDelimiter("\\Z").next();
        Type employeeListType = new TypeToken<ArrayList<Employee>>(){}.getType();
        return gson.<ArrayList<Employee>>fromJson(json, employeeListType);
    }

    @Override
    public void saveEmployeesToFile(String path, List<Employee> employees) throws IOException {
        File file = new File(path);
        PrintStream out = new PrintStream(file);
        String json = gson.toJson(employees);
        out.print(json);
    }
}


















