package com.mozper.codingtest.data

import com.mozper.codingtest.data.db.EmployeesDao
import com.mozper.codingtest.data.model.Employee
import com.mozper.codingtest.data.net.EmployeesApi
import javax.inject.Inject

class EmployeesRepository @Inject constructor(
    private val employeesDao: EmployeesDao,
    private val employeesApi: EmployeesApi
) {

    suspend fun getAllEmployees(): List<Employee> {
        val employees = employeesDao.getAll()

        return if (employees.isEmpty()) {
            fetchEmployees()
        } else {
            employees
        }
    }

    private suspend fun fetchEmployees(): List<Employee> {
        val employeesFromApi = employeesApi.getEmployees()
        employeesDao.createAll(employeesFromApi.employees)
        return employeesFromApi.employees
        return listOf()
    }

    suspend fun getEmployee(id: Int): Employee {
        return employeesDao.getEmployee(id)
    }
}