package com.mozper.codingtest.presentation.employees

import android.os.Bundle
import android.view.View
import com.mozper.codingtest.R
import com.mozper.codingtest.databinding.FragmentEmployeesDetailsBinding
import com.mozper.codingtest.presentation.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesDetailsFragment : BaseViewModelFragment<FragmentEmployeesDetailsBinding, EmployeesViewModel>() {

    override val layoutId = R.layout.fragment_employees_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.fetchEmployee(it) }
    }
}