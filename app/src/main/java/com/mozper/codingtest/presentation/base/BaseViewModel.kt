package com.mozper.codingtest.presentation.base

import android.os.Bundle
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract fun init(bundle: Bundle?)
}