package com.mozper.codingtest.presentation.splash

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mozper.codingtest.domain.AuthenticationProvider
import com.mozper.codingtest.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val authenticationProvider: AuthenticationProvider
) : BaseViewModel() {

    private val _navigation = MutableLiveData<Navigation?>()
    val navigation: LiveData<Navigation?>
        get() = _navigation

    override fun init(bundle: Bundle?) {
        if(authenticationProvider.isLoggedIn()) {
            _navigation.postValue(Navigation.Authenticated)
        } else {
            _navigation.postValue(Navigation.NotAuthenticated)
        }
    }

    sealed class Navigation {
        object Authenticated : Navigation()
        object NotAuthenticated : Navigation()
    }
}