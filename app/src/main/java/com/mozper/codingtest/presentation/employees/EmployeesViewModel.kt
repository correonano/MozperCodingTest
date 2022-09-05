package com.mozper.codingtest.presentation.employees

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mozper.codingtest.data.model.Employee
import com.mozper.codingtest.domain.AuthenticationProvider
import com.mozper.codingtest.domain.EmployeesProvider
import com.mozper.codingtest.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val employeesProvider: EmployeesProvider,
    private val authenticationProvider: AuthenticationProvider,
) : BaseViewModel() {

    private val _navigation = MutableLiveData<Navigation?>()
    val navigation: LiveData<Navigation?>
        get() = _navigation

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>>
        get() = _employees

    private val _employee = MutableLiveData<Employee?>()
    val employee: LiveData<Employee?>
        get() = _employee

    private val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->
        _navigation.postValue(Navigation.Error(throwable.message))
    }

    override fun init(bundle: Bundle?) {
    }

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            val employees = employeesProvider.getEmployees()
            withContext(Dispatchers.Main) {
                _employees.postValue(employees)
            }
        }
    }

    fun fetchEmployee(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            val employee = employeesProvider.getEmployee(id)
            withContext(Dispatchers.Main) {
                _employee.postValue(employee)
            }
        }
    }

    fun onItemClick(id: Int) {
        _navigation.postValue(Navigation.ItemClick(id))
    }

    fun logout() = authenticationProvider.logout()

    fun clearNavigation() {
        _navigation.postValue(null)
        _employee.postValue(null)
    }

    sealed class Navigation {
        class ItemClick(val id: Int) : Navigation()
        class Error(val message: String?) : Navigation()
    }
}