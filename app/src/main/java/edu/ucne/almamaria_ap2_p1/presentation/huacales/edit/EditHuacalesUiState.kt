package edu.ucne.almamaria_ap2_p1.presentation.huacales.edit

import com.google.type.DateTime

data class EditHuacalesUiState(
    val idEntrada: Int? = null,
    val fecha: String = "",
    val nombreCliente: String = "",
    val cantidad: String = "",
    val precio: String = "",
    val nombreError: String? = null,
    val fechaError: String? = null,
    val cantidadError: String? = null,
    val precioError: String? = null,

    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val isNew: Boolean = true,
    val saved: Boolean = false,
    val deleted: Boolean = false

)