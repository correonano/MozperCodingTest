package com.mozper.codingtest.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.mozper.codingtest.BR
import java.lang.reflect.ParameterizedType

abstract class BaseViewModelActivity<B: ViewDataBinding, VM: BaseViewModel> : AppCompatActivity() {
    internal lateinit var viewModel: VM
    internal lateinit var binding: B
    open val bindingEnabled = true
    open val layoutId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = buildViewModel()
        if (bindingEnabled) {
            binding = DataBindingUtil.setContentView(this, layoutId)
            binding.lifecycleOwner = this
            binding.setVariable(BR.viewModel, viewModel)
        }
        viewModel.init(intent.extras)
    }

    private fun buildViewModel(): VM {
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>

        return ViewModelProvider(this, defaultViewModelProviderFactory).get(viewModelClass)
    }
}