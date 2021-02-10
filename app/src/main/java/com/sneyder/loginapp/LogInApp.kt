package com.sneyder.loginapp

import android.os.StrictMode
import com.sneyder.loginapp.utils.BaseApp
import timber.log.Timber

class LogInApp : BaseApp() {

    override fun onCreate() {
        setStrictMode()
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(MyCustomDebugTree())
    }

    class MyCustomDebugTree: Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? {
            return "${super.createStackElementTag(element)}:${element.lineNumber}"
        }
    }

    /**
     * Set up strict mode for debugging
     * Note: do not enable penaltyDeath because it cause some false positives that shoot down the app
     */
    private fun setStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                //.penaltyDeath()
                .build())
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                //.penaltyDeath()
                .build())
    }

}