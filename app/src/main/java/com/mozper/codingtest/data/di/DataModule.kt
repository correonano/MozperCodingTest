package com.mozper.codingtest.data.di

import android.content.Context
import androidx.room.Room
import com.mozper.codingtest.data.AuthenticationRepository
import com.mozper.codingtest.data.EmployeesRepository
import com.mozper.codingtest.data.db.EmployeesDB
import com.mozper.codingtest.data.db.EmployeesDao
import com.mozper.codingtest.data.net.EmployeesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val URL = "https://demo3535907.mockable.io/"

    @Singleton
    @Provides
    fun provideAuthenticationRepository(@ApplicationContext context: Context): AuthenticationRepository {
        return AuthenticationRepository(context)
    }

    @Singleton
    @Provides
    fun provideEmployeesRepository(employeesDao: EmployeesDao, employeesApi: EmployeesApi): EmployeesRepository {
        return EmployeesRepository(employeesDao, employeesApi)
    }

    @Singleton
    @Provides
    fun provideEmployeesDB(@ApplicationContext context: Context) : EmployeesDB {
        return Room.databaseBuilder(
            context,
            EmployeesDB::class.java, "database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideEmployeesDao(employeesDB: EmployeesDB): EmployeesDao {
        return employeesDB.employeesDao()
    }

    @Singleton
    @Provides
    fun provideEmployeesApi(retrofit: Retrofit): EmployeesApi {
        return retrofit.create(EmployeesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

}