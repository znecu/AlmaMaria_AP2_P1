package edu.ucne.almamaria_ap2_p1.domain.huacales.model

import com.google.type.DateTime

data class Huacales(
    val idEntrada: Int = 0,
    val Fecha: DateTime,
    val NombreCliente: String = "",
    val Cantidad: Int,
    val Precio: Double
)
