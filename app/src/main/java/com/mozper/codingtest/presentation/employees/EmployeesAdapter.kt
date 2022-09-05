package com.mozper.codingtest.presentation.employees

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mozper.codingtest.BR
import com.mozper.codingtest.R
import com.mozper.codingtest.data.model.Employee
import com.mozper.codingtest.databinding.EmployeesLineBinding

class EmployeesAdapter(private val employees: List<Employee>, private val viewModel: EmployeesViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(employees[position], viewModel)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binder = DataBindingUtil.inflate<EmployeesLineBinding>(
            LayoutInflater.from(parent.context),
            R.layout.employees_line,
            parent,
            false
        )
        return ViewHolder(binder)
    }

    class ViewHolder(binder: EmployeesLineBinding) : RecyclerView.ViewHolder(binder.root) {

        var v: EmployeesLineBinding = binder

        fun bind(employee: Employee, viewModel: EmployeesViewModel) {
            v.setVariable(BR.item, employee)
            v.setVariable(BR.viewModel, viewModel)
            v.executePendingBindings()
        }
    }
}