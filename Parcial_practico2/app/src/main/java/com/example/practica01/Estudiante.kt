package com.example.practica01

class Estudiante {
    fun key(key: String?) {
    }
    var nombre: String? = null
    var nota1: Float? = null
    var nota2: Float? = null
    var nota3: Float? = null
    var nota4: Float? = null
    var nota5: Float? = null
    var promedio: Float? = null
    var key: String? = null
    var per: MutableMap<String, Boolean> = HashMap()
    constructor() {}
    constructor(nombre: String?, nota1: Float?, nota2: Float?, nota3: Float?, nota4: Float?, nota5: Float?, promedio: Float?) {
        this.nombre = nombre
        this.nota1 = nota1
        this.nota2 = nota2
        this.nota3 = nota3
        this.nota4 = nota4
        this.nota5 = nota5
        this.promedio = promedio
    }
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nombre" to nombre,
            "nota1" to nota1,
            "nota2" to nota2,
            "nota3" to nota3,
            "nota4" to nota4,
            "nota5" to nota5,
            "promedio" to promedio,
            "key" to key,
            "per" to per
        )
    }
}