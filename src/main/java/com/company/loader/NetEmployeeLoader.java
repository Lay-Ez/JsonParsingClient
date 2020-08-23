package com.company.loader;

import com.company.model.Employee;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

public class NetEmployeeLoader implements EmployeeLoader {

    private JsonGitHubApi jsonGitHubApi;

    public NetEmployeeLoader(JsonGitHubApi jsonGitHubApi) {
        this.jsonGitHubApi = jsonGitHubApi;
    }

    @Override
    public List<Employee> loadEmployees() throws IOException {
        Call<List<Employee>> call = jsonGitHubApi.getEmployees();
        return call.execute().body();
    }
}
