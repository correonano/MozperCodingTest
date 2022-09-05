package com.mozper.codingtest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mozper.codingtest.data.model.Employee

@Dao
interface EmployeesDao {

    @Query("SELECT * FROM employee")
    fun getAll(): List<Employee>

    @Query("SELECT * FROM employee WHERE id == :id")
    fun getEmployee(id: Int): Employee

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createAll(objects: List<Employee>)

}