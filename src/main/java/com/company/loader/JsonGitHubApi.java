package com.company.loader;

import com.company.model.Employee;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface JsonGitHubApi {

    @GET("employees")
    Call<List<Employee>> getEmployees();
}
