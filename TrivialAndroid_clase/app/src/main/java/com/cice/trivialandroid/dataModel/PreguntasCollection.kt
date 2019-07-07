package com.cice.trivialandroid.dataModel

import java.io.Serializable

data class PreguntasCollection(
        var preguntas : ArrayList<Pregunta>
):Serializable


data class Pregunta(var enunciado : String = "",
                    var respuesta : ArrayList<String>,
                    var correcta : Int,
                    var image_url : String = ""):Serializable



