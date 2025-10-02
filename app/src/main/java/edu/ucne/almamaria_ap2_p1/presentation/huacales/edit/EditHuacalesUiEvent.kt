package edu.ucne.almamaria_ap2_p1.presentation.huacales.edit

sealed interface EditHuacalesUiEvent {
    data class Load(val id: Int?): EditHuacalesUiEvent
    data class NombreChanged(val value: String): EditHuacalesUiEvent
    data class FechaChanged(val value: String): EditHuacalesUiEvent
    data class CantidadChanged(val value: String): EditHuacalesUiEvent
    data class PrecioChanged(val value: String): EditHuacalesUiEvent

    data object Save: EditHuacalesUiEvent
    data object Delete: EditHuacalesUiEvent
}