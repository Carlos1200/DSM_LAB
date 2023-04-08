package com.example.practica01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

lateinit var valor1 : EditText
lateinit var valor2 : EditText
lateinit var enviarSuma : Button
lateinit var enviarResta : Button
lateinit var enviarMultiplicar : Button
lateinit var enviarDividir : Button
lateinit var resultadoOperacion: TextView

class ThirdFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        valor1 = getView()?.findViewById(R.id.TxtValor1)!!
        valor2 = getView()?.findViewById(R.id.TxtValor2)!!
        enviarSuma = getView()?.findViewById(R.id.BtnSuma)!!
        enviarResta = getView()?.findViewById(R.id.BtnResta)!!
        enviarMultiplicar = getView()?.findViewById(R.id.BtnMultiplicar)!!
        enviarDividir = getView()?.findViewById(R.id.BtnDividir)!!

        resultadoOperacion = getView()?.findViewById(R.id.LlbResultadoOperacion)!!

        enviarSuma.setOnClickListener {
            var resultado:Float = ((if(valor1.text.toString().isNullOrEmpty()) "0" else valor1.text.toString() ).toFloat() + (if(valor2.text.toString().isNullOrEmpty()) "0" else valor2.text.toString() ).toFloat() )
            resultadoOperacion.setText("Resultado: "+resultado.toString())
        }
        enviarResta.setOnClickListener {
            var resultado:Float = ((if(valor1.text.toString().isNullOrEmpty()) "0" else valor1.text.toString() ).toFloat() - (if(valor2.text.toString().isNullOrEmpty()) "0" else valor2.text.toString() ).toFloat() )
            resultadoOperacion.setText("Resultado: "+resultado.toString())
        }
        enviarMultiplicar.setOnClickListener {
            var resultado:Float = ((if(valor1.text.toString().isNullOrEmpty()) "0" else valor1.text.toString() ).toFloat() * (if(valor2.text.toString().isNullOrEmpty()) "0" else valor2.text.toString() ).toFloat() )
            resultadoOperacion.setText("Resultado: "+resultado.toString())
        }
        enviarDividir.setOnClickListener {
            var resultado:Float = ((if(valor1.text.toString().isNullOrEmpty()) "0" else valor1.text.toString() ).toFloat() / (if(valor2.text.toString().isNullOrEmpty()) "1" else valor2.text.toString() ).toFloat() )
            resultadoOperacion.setText("Resultado: "+resultado.toString())
        }
    }
}