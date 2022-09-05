package com.mozper.codingtest.presentation.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.mozper.codingtest.R
import com.mozper.codingtest.databinding.ActivitySplashBinding
import com.mozper.codingtest.presentation.base.BaseViewModelActivity
import com.mozper.codingtest.presentation.employees.EmployeesActivity
import com.mozper.codingtest.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SplashActivity : BaseViewModelActivity<ActivitySplashBinding, SplashViewModel>() {

    override val layoutId = R.layout.activity_splash

    private val SPLASH_DISPLAY_LENGHT: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigation.observe(this, Observer {
            when (it) {
                is SplashViewModel.Navigation.Authenticated -> {
                    Observable.timer(SPLASH_DISPLAY_LENGHT, TimeUnit.MILLISECONDS).subscribe {
                        startActivity(EmployeesActivity.getIntent(this))
                        finish()
                    }
                }
                is SplashViewModel.Navigation.NotAuthenticated -> {
                    Observable.timer(SPLASH_DISPLAY_LENGHT, TimeUnit.MILLISECONDS).subscribe {
                        startActivity(LoginActivity.getIntent(this))
                        finish()
                    }
                }
            }
        })
    }
}