package com.sneyder.loginapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.sneyder.loginapp.utils.base.DaggerActivity
import com.sneyder.loginapp.R

class MainActivity : DaggerActivity() {

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}