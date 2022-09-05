package com.mozper.codingtest.presentation.employees

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mozper.codingtest.R
import com.mozper.codingtest.databinding.FragmentEmployeesListBinding
import com.mozper.codingtest.presentation.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesListFragment :
    BaseViewModelFragment<FragmentEmployeesListBinding, EmployeesViewModel>() {

    override val layoutId = R.layout.fragment_employees_list

    private lateinit var adapter: EmployeesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetch()
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.employees.observe(viewLifecycleOwner, Observer {
            binding.progress.visibility = View.GONE
            adapter = EmployeesAdapter(it, viewModel)
            binding.recycler.adapter = adapter
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            when (it) {
                is EmployeesViewModel.Navigation.ItemClick -> {
                    view.findNavController().navigate(EmployeesListFragmentDirections.actionEmployeesDetailsFragment(it.id))
                }
                is EmployeesViewModel.Navigation.Error -> {
                    binding.progress.visibility = View.GONE
                    Snackbar.make(
                        binding.root,
                        it.message ?: getString(R.string.generic_error),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}