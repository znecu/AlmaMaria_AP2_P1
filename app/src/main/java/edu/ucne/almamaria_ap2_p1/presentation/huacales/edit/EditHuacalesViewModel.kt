package edu.ucne.almamaria_ap2_p1.presentation.huacales.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.almamaria_ap2_p1.domain.huacales.model.Huacales
import edu.ucne.almamaria_ap2_p1.domain.huacales.usecase.DeleteHuacalesUseCase
import edu.ucne.almamaria_ap2_p1.domain.huacales.usecase.GetHuacalesUseCase
import edu.ucne.almamaria_ap2_p1.domain.huacales.usecase.UpsertHuacalesUseCase
import edu.ucne.almamaria_ap2_p1.domain.huacales.usecase.validateCantidad
import edu.ucne.almamaria_ap2_p1.domain.huacales.usecase.validateNombre
import edu.ucne.almamaria_ap2_p1.domain.huacales.usecase.validatePrecio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditHuacalesViewModel @Inject constructor(
    private val getHuacalesUseCase: GetHuacalesUseCase,
    private val upsertHuacalesUseCase: UpsertHuacalesUseCase,
    private val deleteHuacalesUseCase: DeleteHuacalesUseCase
): ViewModel() {
    private val _state = MutableStateFlow(value = EditHuacalesUiState())
    val state: StateFlow<EditHuacalesUiState> = _state.asStateFlow()

    fun onEvent(event: EditHuacalesUiEvent){
        when(event){
            is EditHuacalesUiEvent.Load -> onLoad(event.id)
            is EditHuacalesUiEvent.NombreChanged -> _state.update {
                it.copy(nombreCliente = event.value, nombreError = null)
            }
            is EditHuacalesUiEvent.CantidadChanged -> _state.update {
                it.copy(cantidad = event.value, cantidadError = null)
            }
            is EditHuacalesUiEvent.PrecioChanged -> _state.update {
                it.copy(precio = event.value, precioError = null)
            }
            is EditHuacalesUiEvent.FechaChanged -> _state.update {
                it.copy(fecha = event.value, fechaError = null)
            }
            EditHuacalesUiEvent.Save -> onSave()
            EditHuacalesUiEvent.Delete -> onDelete()
        }
    }
    private fun onLoad(id:Int?){
        if (id == null || id == 0){
            _state.update { it.copy(isNew = true, idEntrada = null) }
            return
        }
        viewModelScope.launch {
            val huaceles = getHuacalesUseCase(id)
            if(huaceles!=null){
                _state.update {
                    it.copy(
                        isNew = false,
                        idEntrada = huaceles.idEntrada,
                        fecha = huaceles.fecha,
                        nombreCliente = huaceles.nombreCliente,
                        cantidad = huaceles.cantidad.toString(),
                        precio = huaceles.precio.toString()
                    )
                }
            }
        }
    }
    private fun onSave(){
        val nombreCliente = state.value.nombreCliente
        val nombreValidations = validateNombre(nombreCliente)
        val cantidad = state.value.cantidad
        val cantidadValidations = validateCantidad(cantidad)
        val precio = state.value.cantidad
        val precioValidations = validatePrecio(precio)
        val fecha = state.value.fecha

        if(!nombreValidations.isValid || !cantidadValidations.isValid || precioValidations.isValid){
            _state.update {
                it.copy(
                    nombreError = nombreValidations.error,
                    cantidadError = cantidadValidations.error,
                    precioError = precioValidations.error
                )
            }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val id = state.value.idEntrada ?: 0
            val huacales = Huacales(
                idEntrada = id,
                nombreCliente = nombreCliente,
                cantidad = cantidad.toInt(),
                precio = precio.toDouble(),
                fecha = fecha
            )
            val result = upsertHuacalesUseCase(huacales)
            result.onSuccess { newId ->
                _state.value = EditHuacalesUiState()
            }.onFailure { e ->
                _state.update { it.copy(isSaving = false) }

            }
        }
    }
    private fun onDelete(){
        val id = state.value.idEntrada ?: return
        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            deleteHuacalesUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }

}