package com.mozper.codingtest.domain

import com.mozper.codingtest.data.EmployeesRepository
import javax.inject.Inject

class EmployeesProvider @Inject constructor(var employeesRepository: EmployeesRepository) {

    suspend fun getEmployees() = employeesRepository.getAllEmployees()

    suspend fun getEmployee(id: Int) = employeesRepository.getEmployee(id)

}