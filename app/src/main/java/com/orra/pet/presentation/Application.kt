package com.orra.pet.presentation

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication

class Application : MultiDexApplication() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        Application.context = this
    }

}