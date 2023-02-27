package com.example.practica01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

lateinit var nombre : EditText
lateinit var dato1 : EditText
lateinit var dato2 : EditText
lateinit var dato3 : EditText
lateinit var dato4 : EditText
lateinit var dato5 : EditText
lateinit var enviar : Button
lateinit var resultado: TextView

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nombre = getView()?.findViewById(R.id.TxtNombre)!!
        dato1 = getView()?.findViewById(R.id.TxtDato1)!!
        dato2 = getView()?.findViewById(R.id.TxtDato2)!!
        dato3 = getView()?.findViewById(R.id.TxtDato3)!!
        dato4 = getView()?.findViewById(R.id.TxtDato4)!!
        dato5 = getView()?.findViewById(R.id.TxtDato5)!!
        enviar = getView()?.findViewById(R.id.BtnEnviar)!!
        resultado = getView()?.findViewById(R.id.LlbResultado)!!

        enviar.setOnClickListener {
            var nombreString:String = if (nombre.text.toString().isNullOrEmpty()) "Nombre" else nombre.text.toString()
            var valor1: Float = (if(dato1.text.toString().isNullOrEmpty()) "0" else dato1.text.toString() ).toFloat()
            var valor2: Float = (if (dato2.text.toString().isNullOrEmpty()) "0" else dato2.text.toString() ).toFloat()
            var valor3: Float = (if (dato3.text.toString().isNullOrEmpty())"0" else dato3.text.toString()  ).toFloat()
            var valor4: Float = (if (dato4.text.toString().isNullOrEmpty())"0" else dato4.text.toString()  ).toFloat()
            var valor5: Float = (if (dato5.text.toString().isNullOrEmpty())"0" else dato5.text.toString()).toFloat()
            var resultadoFinal: String;
            var promedio: Float = (valor1+valor2+valor3+valor4+valor5)/5
            if(promedio >= 6){
                resultadoFinal = "Aprobado"
            }else{
                resultadoFinal = "Reprobado"
            }
            resultado.setText("El alumno " + nombreString+ " ha sacado una nota de "+promedio.toString()+ " y ha "+ resultadoFinal )
        }
    }
}