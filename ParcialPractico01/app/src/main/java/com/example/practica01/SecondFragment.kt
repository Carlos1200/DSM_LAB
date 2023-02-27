package com.example.practica01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

lateinit var nombreEmpleado : EditText
lateinit var salario : EditText
lateinit var enviarSalario : Button
lateinit var resultadoSalario: TextView

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nombreEmpleado = getView()?.findViewById(R.id.TxtNombreEmpleado)!!
        salario = getView()?.findViewById(R.id.TxtSalario)!!
        enviarSalario = getView()?.findViewById(R.id.BtnEnviarSalario)!!
        resultadoSalario = getView()?.findViewById(R.id.LlbResultadoSalario)!!
        enviarSalario.setOnClickListener {
            var valor1: Float = (if(salario.text.toString().isNullOrEmpty()) "0" else salario.text.toString() ).toFloat()
            var salarioNeto: Float = (valor1-(valor1*0.03)-(valor1*0.04)-(valor1*0.05)).toFloat()
            resultadoSalario.setText("El Empleado " + nombreEmpleado.text.toString()+ " tiene un salario neto de: "+ salarioNeto.toString() )
        }
    }

}