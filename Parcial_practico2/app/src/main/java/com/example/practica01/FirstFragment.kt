package com.example.practica01

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

lateinit var nombre : EditText
lateinit var dato1 : EditText
lateinit var dato2 : EditText
lateinit var dato3 : EditText
lateinit var dato4 : EditText
lateinit var dato5 : EditText
lateinit var enviar : Button
lateinit var resultado: TextView

class FirstFragment : Fragment() {
    var consultaOrdenada: Query = refEstudiantes.orderByChild("nombre")
    var estudiantes: MutableList<Estudiante>? = null
    var listaEstudiantes: ListView? = null
    var labelKey: String = ""
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
        iniciar()
        enviar.setOnClickListener {
            if (nombre.text.toString().isNullOrEmpty() || dato1.text.toString().isNullOrEmpty() || dato2.text.toString().isNullOrEmpty() || dato3.text.toString().isNullOrEmpty() || dato4.text.toString().isNullOrEmpty() || dato5.text.toString().isNullOrEmpty()){
                resultado.setText("Debe llenar todos los campos")
            }else{
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
                val newEstudiante = Estudiante(nombreString, valor1, valor2, valor3, valor4, valor5, promedio)
                if(labelKey.isNullOrEmpty()){
                    refEstudiantes.child(nombreString).setValue(newEstudiante).addOnSuccessListener {
                        Toast.makeText(activity, "Estudiante agregado", Toast.LENGTH_SHORT).show()
                        nombre.setText("")
                        dato1.setText("")
                        dato2.setText("")
                        dato3.setText("")
                        dato4.setText("")
                        dato5.setText("")
                    }.addOnFailureListener {
                        Toast.makeText(activity, "Error al agregar estudiante", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    refEstudiantes.child(labelKey).setValue(newEstudiante).addOnSuccessListener {
                        Toast.makeText(activity, "Estudiante actualizado", Toast.LENGTH_SHORT).show()
                        nombre.setText("")
                        dato1.setText("")
                        dato2.setText("")
                        dato3.setText("")
                        dato4.setText("")
                        dato5.setText("")
                        labelKey = ""
                    }.addOnFailureListener {
                        Toast.makeText(activity, "Error al actualizar estudiante", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    private fun iniciar(){
        listaEstudiantes = getView()?.findViewById<ListView>(R.id.ListaEstudiantes)!!

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por más de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaEstudiantes!!.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { adapterView, view, position, l -> // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                val ad = AlertDialog.Builder(activity)
                ad.setMessage("Está seguro de eliminar registro?")
                    .setTitle("Confirmación")
                ad.setPositiveButton("Si"
                ) { _, _ ->
                    estudiantes!![position].nombre?.let {
                        refEstudiantes.child(it).removeValue()
                    }
                    Toast.makeText(
                        activity,
                        "Registro borrado!", Toast.LENGTH_SHORT
                    ).show()
                }
                ad.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        Toast.makeText(
                            activity,
                            "Operación de borrado cancelada!", Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                ad.show()
                true
            }

        listaEstudiantes!!.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                labelKey = estudiantes!![position].nombre.toString()
                nombre.setText(labelKey)
                dato1.setText(estudiantes!![position].nota1.toString())
                dato2.setText(estudiantes!![position].nota2.toString())
                dato3.setText(estudiantes!![position].nota3.toString())
                dato4.setText(estudiantes!![position].nota4.toString())
                dato5.setText(estudiantes!![position].nota5.toString())
            }
        estudiantes = ArrayList<Estudiante>()
        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                estudiantes!!.removeAll(estudiantes!!)
                for (dato in dataSnapshot.getChildren()) {
                    val persona: Estudiante? = dato.getValue(Estudiante::class.java)
                    persona?.key(dato.key)
                    if (persona != null) {
                        estudiantes!!.add(persona)
                    }
                }
                val adapter = EstudianteAdapter(
                    activity!!,
                    estudiantes as ArrayList<Estudiante>
                )
                listaEstudiantes!!.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
    companion object {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var refEstudiantes: DatabaseReference = database.getReference("estudiantes")
    }
}