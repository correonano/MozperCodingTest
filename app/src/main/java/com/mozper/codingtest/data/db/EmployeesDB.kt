package com.mozper.codingtest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mozper.codingtest.data.model.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeesDB : RoomDatabase() {
    abstract fun employeesDao(): EmployeesDao
}