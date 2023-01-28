package com.example.discusionderesultados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

lateinit var dato1 : EditText
lateinit var dato2 : EditText
lateinit var enviar : Button
lateinit var resultado: TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dato1 = findViewById(R.id.TxtDato1)
        dato2 = findViewById(R.id.TxtDato2)
        enviar = findViewById(R.id.BtnEnviar)
        resultado = findViewById(R.id.LlbResultado)
        enviar.setOnClickListener{
            var valor1: Float = dato1.text.toString().toFloat()
            var valor2: Float = dato2.text.toString().toFloat()

            resultado.setText((valor1+valor2).toString())
        }

    }
}