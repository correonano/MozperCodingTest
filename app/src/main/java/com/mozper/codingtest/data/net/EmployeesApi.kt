package com.mozper.codingtest.data.net

import com.mozper.codingtest.data.model.Employee
import com.mozper.codingtest.data.model.Employees
import retrofit2.http.GET

interface EmployeesApi {

    @GET(".")
    suspend fun getEmployees() : Employees

}