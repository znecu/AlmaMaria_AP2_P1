package edu.ucne.almamaria_ap2_p1.domain.huacales.model

import com.google.type.DateTime

data class Huacales(
    val idEntrada: Int = 0,
    val fecha: String = "",
    val nombreCliente: String = "",
    val cantidad: Int,
    val precio: Double
)
