package com.cice.trivialandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.cice.trivialandroid.auth_activity.AuthActivity
import com.firebase.ui.auth.AuthUI
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {


    private var drawerLayout : DrawerLayout? = null //este maneja la estructura del layout (apertura/cierre)
    private var drawerView : NavigationView? = null //este maneja el contenido del Drawer (eventos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(myToolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        drawerView = findViewById(R.id.nav_view)

        //Manager de apertura y cierre de menu
        val toogle = ActionBarDrawerToggle(this,
                drawerLayout,
                myToolbar,
                R.string.menu_open,
                R.string.menu_close)
        drawerLayout?.addDrawerListener(toogle)
        toogle.syncState()



        //Sacamos los datos del usuario si ya hay Login
        val user = (application as MyApplication).myCurrentUser

        //evento que nos asegura que el menuDrawer ya está disponible a nivel visual
        drawerLayout?.setOnSystemUiVisibilityChangeListener {
            textViewDrawerUserName.text = user.displayName
            textViewDrawerUserMail.text = user.email
            Picasso.get()
                    .load(user.photoUrl)
                    .into(imageViewDrawerUserAvatar)
        }

        //listener para escuchar los eventos de botones en el menu Drawer
        drawerView?.setNavigationItemSelectedListener (this) //esto conecta con onNavigationItemSelected


       /* if(user != null)
        {
            Log.i("myApp","User Name -> ${user.displayName}" )
            Log.i("myApp","User Mail -> ${user.email}" )
            Log.i("myApp","User Avatar URL -> ${user.photoUrl}" )
            Log.i("myApp","User Mail Verified -> ${user.isEmailVerified}" )
            Log.i("myApp","User uID -> ${user.uid}" )
        }*/
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.i("myApp","Boton pulsado en menu drawer")

        if(item.itemId == R.id.nav_close_session)
        {
            //Cierre de sesion Esto es auxiliar, luego irá en otro sitio
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener{
                        //Toast.makeText(this,"El usuario ha cerrado la sesion",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, AuthActivity::class.java))
                    }
        }
        else if(item.itemId == R.id.nav_delete_session)
        {
            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this)
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
                alertDialog.setTitle("¿Quieres borrar tu usuario?")
                alertDialog.setMessage("Si borras el usuario perderás tu progreso")

                alertDialog.setPositiveButton("SI"){dialog, which ->
                    AuthUI.getInstance()
                            .delete(this)
                            .addOnCompleteListener{
                                startActivity(Intent(this, AuthActivity::class.java))
                            }
                }

                alertDialog.setNegativeButton("NO"){dialog, which ->
                }

            alertDialog.create().show()
        }
        else if(item.itemId == R.id.nav_new_match)
        {
            startActivity(Intent(this, GameActivity::class.java))
        }


        return true
    }
}
