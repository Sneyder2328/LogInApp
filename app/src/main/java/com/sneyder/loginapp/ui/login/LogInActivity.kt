package com.sneyder.loginapp.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.sneyder.loginapp.utils.base.DaggerActivity
import com.sneyder.loginapp.R

class LogInActivity : DaggerActivity() {

    private val viewModel: LogInViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
    }
}