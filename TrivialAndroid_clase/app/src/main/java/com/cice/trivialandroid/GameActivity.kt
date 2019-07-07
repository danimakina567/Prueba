package com.cice.trivialandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import android.util.Log
import com.cice.trivialandroid.dataModel.ApiService
import com.cice.trivialandroid.dataModel.PreguntasCollection
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.FileDownloadTask
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        getPeguntas()
    }


    fun getPeguntas()
    {
       val retrofit : Retrofit = Retrofit.Builder()
               .baseUrl("https://firebasestorage.googleapis.com/v0/b/trivialandroid-8ea26.appspot.com/o/")
               .addConverterFactory(GsonConverterFactory.create())
               .build()

        val service : ApiService = retrofit.create<ApiService>(ApiService::class.java)

        service.getPreguntasData().enqueue(object : Callback<PreguntasCollection>{
            override fun onFailure(call: Call<PreguntasCollection>, t: Throwable) {
                Log.e("myApp", "Error-> ${t.printStackTrace()}")
            }

            override fun onResponse(call: Call<PreguntasCollection>, response: Response<PreguntasCollection>) {
                    Log.i("myApp", response?.body()!!.preguntas[0].enunciado)
            }

        })
    }
}
