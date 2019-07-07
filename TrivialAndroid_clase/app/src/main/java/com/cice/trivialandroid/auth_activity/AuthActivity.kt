package com.cice.trivialandroid.auth_activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.cice.trivialandroid.MainActivity
import com.cice.trivialandroid.MyApplication

//import de R porque esta activity esta dentro de un custom package
import com.cice.trivialandroid.R
import com.cice.trivialandroid.auth_activity.auth_fragments.LoginFragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    val providers = arrayListOf<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build()
    )

    val REQUEST_CODE_SIGN_IN_ACTIVITY = 1234

    lateinit var loginFragment : LoginFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //lanzamos el fragment Login
        loginFragment = LoginFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.authFragmentPlace,loginFragment,"loginFragment").commit()
    }


    //esta funcion es llamada desde un boton que hay en el fragment Login, va sin
    //inteface porque usamos el truco de onClick (no abusar)
    fun onGoogleLoginButonPressed(view : View){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.ic_launcher_foreground)
                        .setTosAndPrivacyPolicyUrls("","")
                        .build()
                ,REQUEST_CODE_SIGN_IN_ACTIVITY)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SIGN_IN_ACTIVITY)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                val user = FirebaseAuth.getInstance().currentUser

                if(user != null)
                {
                    (application as MyApplication).myCurrentUser = user!!
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
            else
            {
               Toast.makeText(this,"fallo en login Google",Toast.LENGTH_LONG).show()
            }
        }
    }
}
