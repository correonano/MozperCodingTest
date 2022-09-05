package com.mozper.codingtest.domain.di

import com.mozper.codingtest.data.AuthenticationRepository
import com.mozper.codingtest.data.EmployeesRepository
import com.mozper.codingtest.domain.AuthenticationProvider
import com.mozper.codingtest.domain.EmployeesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideAuthenticationProvider(authenticationRepository: AuthenticationRepository) : AuthenticationProvider {
        return AuthenticationProvider(authenticationRepository)
    }

    @Singleton
    @Provides
    fun provideEmployeesProvider(employeesRepository: EmployeesRepository) : EmployeesProvider {
        return EmployeesProvider(employeesRepository)
    }

}