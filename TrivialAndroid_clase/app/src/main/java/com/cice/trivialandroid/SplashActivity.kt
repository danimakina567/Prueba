package com.cice.trivialandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cice.trivialandroid.auth_activity.AuthActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)



        //Get device Token Kotlin
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.i("myApp",it.token)
        }


        //suscripcion
        //FirebaseMessaging.getInstance().subscribeToTopic("promociones")

        FirebaseMessaging.getInstance().unsubscribeFromTopic("promociones")



        if(FirebaseAuth.getInstance().currentUser != null)
        {
            val user = FirebaseAuth.getInstance().currentUser

            (application as MyApplication).myCurrentUser = user!!
            startActivity(Intent(this,MainActivity::class.java))
        }
        else
        {
            startActivity(Intent(this,AuthActivity::class.java))
        }

    }
}
