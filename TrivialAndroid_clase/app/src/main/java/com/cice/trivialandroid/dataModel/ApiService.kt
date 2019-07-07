package com.cice.trivialandroid.dataModel

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("preguntas.json?alt=media&token=56d7bc24-fbe0-444d-94fe-a2c1f83f2fdf")
    fun getPreguntasData(): Call<PreguntasCollection>

}