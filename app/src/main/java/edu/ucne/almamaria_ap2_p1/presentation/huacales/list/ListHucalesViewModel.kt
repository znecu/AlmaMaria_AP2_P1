package edu.ucne.almamaria_ap2_p1.presentation.huacales.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.almamaria_ap2_p1.domain.huacales.usecase.DeleteHuacalesUseCase
import edu.ucne.almamaria_ap2_p1.domain.huacales.usecase.ObserveHuacalesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListHucalesViewModel @Inject constructor(
    private val observeHuacalesUseCase: ObserveHuacalesUseCase,
    private val deleteHuacalesUseCase: DeleteHuacalesUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ListHuacalesUiState(isLoading = true))
    val state: StateFlow<ListHuacalesUiState> = _state.asStateFlow()

    init{
        onEvent(ListHuacalesUiEvent.Load)
    }
    fun onEvent(event: ListHuacalesUiEvent){
        when(event){
            ListHuacalesUiEvent.Load -> observe()
            is ListHuacalesUiEvent.Delete -> onDelete(event.id)
            ListHuacalesUiEvent.CreateNew -> _state.update { it.copy(navigationToCreate = true) }
            is ListHuacalesUiEvent.Edit -> _state.update { it.copy(navigateToEditId = event.id) }
            is ListHuacalesUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }

        }
    }
    private fun observe(){
        viewModelScope.launch {
            observeHuacalesUseCase().collectLatest { list ->
                _state.update { it.copy(isLoading = false, huacales = list, message = null) }
            }
        }
    }
    private fun onDelete(id: Int){
        viewModelScope.launch {
            deleteHuacalesUseCase(id)
            onEvent(ListHuacalesUiEvent.ShowMessage("Eliminado"))
        }
    }

}