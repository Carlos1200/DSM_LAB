package com.example.practica01

import android.app.Activity
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.view.View

class EstudianteAdapter(private val context:Activity,val estudiantes:List<Estudiante>):ArrayAdapter<Estudiante?>(context,R.layout.estudiante_layout,estudiantes) {
    override fun getView(position:Int,view:View?,parent:ViewGroup):View{
        val inflater=context.layoutInflater
        val rowView=inflater.inflate(R.layout.estudiante_layout,null,true)
        val nombre=rowView.findViewById(R.id.tvNombre) as TextView
        val nota1=rowView.findViewById(R.id.tvnota1) as TextView
        val nota2=rowView.findViewById(R.id.tvnota2) as TextView
        val nota3=rowView.findViewById(R.id.tvnota3) as TextView
        val nota4=rowView.findViewById(R.id.tvnota4) as TextView
        val nota5=rowView.findViewById(R.id.tvnota5) as TextView
        val promedio=rowView.findViewById(R.id.tvpromedio) as TextView
        nombre.text=estudiantes[position].nombre
        nota1.text="Nota 1: "+estudiantes[position].nota1.toString()
        nota2.text="Nota 2: "+estudiantes[position].nota2.toString()
        nota3.text="Nota 3: "+estudiantes[position].nota3.toString()
        nota4.text="Nota 4: "+estudiantes[position].nota4.toString()
        nota5.text="Nota 5: "+estudiantes[position].nota5.toString()
        promedio.text="Promedio: "+estudiantes[position].promedio.toString()
        return rowView
    }
}