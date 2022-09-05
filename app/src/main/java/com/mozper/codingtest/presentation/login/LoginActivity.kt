package com.mozper.codingtest.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mozper.codingtest.R
import com.mozper.codingtest.databinding.ActivityLoginBinding
import com.mozper.codingtest.presentation.base.BaseViewModelActivity
import com.mozper.codingtest.presentation.employees.EmployeesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseViewModelActivity<ActivityLoginBinding, LoginViewModel>() {

    override val layoutId = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigation.observe(this, Observer {
            when (it) {
                LoginViewModel.Navigation.LOGIN -> {
                    if (checkFields()) {
                        viewModel.doLogin()
                        startActivity(EmployeesActivity.getIntent(this))
                        finish()
                    }
                }
            }
        })
    }

    private fun checkFields(): Boolean {
        var areFieldsCompleted = true
        if(binding.editUsername.text?.isEmpty() == true) {
            binding.editUsername.setError(getString(R.string.error_username))
            areFieldsCompleted = false
        }
        if(binding.editPassword.text?.isEmpty() == true) {
            binding.editPassword.setError(getString(R.string.error_password))
            areFieldsCompleted = false
        }

        return areFieldsCompleted
    }

    companion object {

        @JvmStatic
        @JvmOverloads
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

    }
}