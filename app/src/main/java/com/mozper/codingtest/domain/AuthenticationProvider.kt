package com.mozper.codingtest.domain

import com.mozper.codingtest.data.AuthenticationRepository
import javax.inject.Inject

class AuthenticationProvider @Inject constructor(var authenticationRepository: AuthenticationRepository) {

    fun logout() = authenticationRepository.logout()

    fun login() = authenticationRepository.login()

    fun isLoggedIn() = authenticationRepository.isLoggedIn()

}