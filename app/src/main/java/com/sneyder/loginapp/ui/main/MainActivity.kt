package com.sneyder.loginapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sneyder.loginapp.utils.base.DaggerActivity
import com.sneyder.loginapp.R

class MainActivity : DaggerActivity() {

    companion object {

        fun starterIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}