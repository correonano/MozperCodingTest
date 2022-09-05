package com.mozper.codingtest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Employee(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val lastName: String,
    val image: String,
    val description: String,
    val rating: Float,
)

data class Employees(val employees: List<Employee>)
