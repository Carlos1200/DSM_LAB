package com.example.practica01

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.*

lateinit var nombreEmpleado : EditText
lateinit var salario : EditText
lateinit var enviarSalario : Button
lateinit var resultadoSalario: TextView

class SecondFragment : Fragment() {
    var consultaOrdenada: Query = refEmpleados.orderByChild("nombre")
    var empleados: MutableList<Empleado>? = null
    var listaEmpleados: ListView? = null
    var labelKey: String? = null
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
        iniciar()
        enviarSalario.setOnClickListener {
            if (nombreEmpleado.text.toString().isNullOrEmpty() || salario.text.toString().isNullOrEmpty()){
                resultadoSalario.setText("Debe llenar todos los campos")
            }else if(salario.text.toString().toFloat() < 0){
                resultadoSalario.setText("El salario no puede ser negativo")
            }
            else{
                var valor1: Float = (if(salario.text.toString().isNullOrEmpty()) "0" else salario.text.toString() ).toFloat()
                var salarioNeto: Float = (valor1-(valor1*0.03)-(valor1*0.04)-(valor1*0.05)).toFloat()
                val empleados = Empleado(nombreEmpleado.text.toString(), salario.text.toString().toFloat(), salarioNeto)
                if(labelKey.isNullOrEmpty()) {
                    refEmpleados.child(nombreEmpleado.text.toString()).setValue(empleados)
                    Toast.makeText(activity, "Empleado agregado", Toast.LENGTH_SHORT).show()
                    nombreEmpleado.setText("")
                    salario.setText("")
                }else{
                    refEmpleados.child(labelKey!!).setValue(empleados)
                    Toast.makeText(activity, "Empleado actualizado", Toast.LENGTH_SHORT).show()
                    nombreEmpleado.setText("")
                    salario.setText("")
                    labelKey = ""
                }

            }

        }
    }

    private fun iniciar(){
        listaEmpleados = getView()?.findViewById<ListView>(R.id.ListaEmpleados)!!

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por más de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaEmpleados!!.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { adapterView, view, position, l -> // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                val ad = AlertDialog.Builder(activity)
                ad.setMessage("Está seguro de eliminar registro?")
                    .setTitle("Confirmación")
                ad.setPositiveButton("Si"
                ) { _, _ ->
                    empleados!![position].nombre?.let {
                        SecondFragment.refEmpleados.child(it).removeValue()
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

        listaEmpleados!!.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                labelKey = empleados!![position].nombre.toString()
                nombreEmpleado.setText(labelKey)
                salario.setText(empleados!![position].salarioBase.toString())
            }
        empleados = ArrayList<Empleado>()
        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                empleados!!.removeAll(empleados!!)
                for (dato in dataSnapshot.getChildren()) {
                    val persona: Empleado? = dato.getValue(Empleado::class.java)
                    persona?.key(dato.key)
                    if (persona != null) {
                        empleados!!.add(persona)
                    }
                }
                val adapter = EmpleadoAdapter(
                    activity!!,
                    empleados as ArrayList<Empleado>
                )
                listaEmpleados!!.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    companion object {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var refEmpleados: DatabaseReference = database.getReference("empleados")
    }

}