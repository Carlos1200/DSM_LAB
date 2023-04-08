package com.example.practica01

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class EmpleadoAdapter(private val context: Activity, val empleados:List<Empleado>):
    ArrayAdapter<Empleado?>(context,R.layout.empleado_layout,empleados) {
    override fun getView(position:Int, view: View?, parent: ViewGroup): View {
        val inflater=context.layoutInflater
        val rowView=inflater.inflate(R.layout.empleado_layout,null,true)
        val nombre=rowView.findViewById(R.id.tvNombre) as TextView
        val salario=rowView.findViewById(R.id.tvSalarioNeto) as TextView
        nombre.text=empleados[position].nombre
        salario.text="Salario neto: "+empleados[position].salarioNeto.toString()
        return rowView
    }
}