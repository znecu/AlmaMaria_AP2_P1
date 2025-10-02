package edu.ucne.almamaria_ap2_p1.presentation.huacales.list

sealed interface ListHuacalesUiEvent {
    data object Load : ListHuacalesUiEvent
    data class Delete(val id: Int) : ListHuacalesUiEvent

    data object CreateNew : ListHuacalesUiEvent
    data class Edit(val id: Int) : ListHuacalesUiEvent
    data class ShowMessage(val message: String) : ListHuacalesUiEvent
}