package com.mozper.codingtest.presentation.employees;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.mozper.codingtest.R
import com.mozper.codingtest.presentation.base.BaseViewModelActivity
import com.mozper.codingtest.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesActivity : BaseViewModelActivity<ViewDataBinding, EmployeesViewModel>() {

    override val bindingEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupActionBarWithNavController(this, findNavController(R.id.navHostFragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        viewModel.clearNavigation()
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.logout -> {
                viewModel.logout()
                startActivity(LoginActivity.getIntent(this))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {

        @JvmStatic
        @JvmOverloads
        fun getIntent(context: Context) = Intent(context, EmployeesActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
    }
}
