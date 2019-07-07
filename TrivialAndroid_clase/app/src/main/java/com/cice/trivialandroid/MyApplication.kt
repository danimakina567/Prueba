package com.cice.trivialandroid

import android.app.Application
import android.util.Log
import com.google.firebase.auth.FirebaseUser

class MyApplication : Application() {

    lateinit var myCurrentUser : FirebaseUser

    override fun onCreate() {
        super.onCreate()
        Log.i("myApp","onCreate in Application")
    }




}