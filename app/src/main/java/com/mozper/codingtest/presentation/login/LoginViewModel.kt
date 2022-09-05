package com.mozper.codingtest.presentation.login

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mozper.codingtest.domain.AuthenticationProvider
import com.mozper.codingtest.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationProvider: AuthenticationProvider
) : BaseViewModel() {

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation>
        get() = _navigation

    override fun init(bundle: Bundle?) {
    }

    fun login() {
        _navigation.postValue(Navigation.LOGIN)
    }

    fun doLogin() {
        authenticationProvider.login()
    }

    enum class Navigation {
        LOGIN
    }
}