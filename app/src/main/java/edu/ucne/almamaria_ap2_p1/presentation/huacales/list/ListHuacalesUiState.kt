package edu.ucne.almamaria_ap2_p1.presentation.huacales.list

import edu.ucne.almamaria_ap2_p1.domain.huacales.model.Huacales

data class ListHuacalesUiState(
    val isLoading: Boolean = false,
    val huacales: List<Huacales> = emptyList(),
    val message: String? = null,
    val navigationToCreate: Boolean = false,
    val navigateToEditId: Int? = null
)