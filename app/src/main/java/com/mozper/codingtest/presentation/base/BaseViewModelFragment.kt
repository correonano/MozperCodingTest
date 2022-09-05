package com.mozper.codingtest.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mozper.codingtest.BR
import java.lang.reflect.ParameterizedType

abstract class BaseViewModelFragment<B: ViewDataBinding, VM: BaseViewModel> : Fragment() {

    internal lateinit var viewModel: VM
    internal lateinit var binding: B
    open val bindingEnabled = true
    open val layoutId = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (bindingEnabled) {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            binding.lifecycleOwner = this
            return binding.root
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = buildViewModel()
        binding.setVariable(BR.viewModel, viewModel)
    }

    private fun buildViewModel(): VM {
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>

        return ViewModelProvider(requireActivity(), requireActivity().defaultViewModelProviderFactory).get(viewModelClass)
    }


}