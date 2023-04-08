package com.example.practica01

class Empleado {
    fun key(key: String?) {
    }
    var nombre: String? = null
    var salarioBase: Float? = null
    var salarioNeto: Float? = null
    var key: String? = null
    var per: MutableMap<String, Boolean> = HashMap()
    constructor() {}
    constructor(nombre: String?, salarioBase: Float?, salarioNeto: Float?) {
        this.nombre = nombre
        this.salarioBase = salarioBase
        this.salarioNeto = salarioNeto
    }
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nombre" to nombre,
            "salarioBase" to salarioBase,
            "salarioNeto" to salarioNeto,
            "key" to key,
            "per" to per
        )
    }
}