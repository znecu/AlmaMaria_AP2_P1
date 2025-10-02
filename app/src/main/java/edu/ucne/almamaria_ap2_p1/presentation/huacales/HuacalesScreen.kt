package edu.ucne.almamaria_ap2_p1.presentation.huacales

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.almamaria_ap2_p1.presentation.componentes.TopBarComponent
import edu.ucne.almamaria_ap2_p1.presentation.huacales.edit.EditHuacalesScreen
import edu.ucne.almamaria_ap2_p1.presentation.huacales.edit.EditHuacalesUiEvent
import edu.ucne.almamaria_ap2_p1.presentation.huacales.edit.EditHuacalesViewModel
import edu.ucne.almamaria_ap2_p1.presentation.huacales.list.ListHuacalesScreen
import edu.ucne.almamaria_ap2_p1.presentation.huacales.list.ListHucalesViewModel
import edu.ucne.almamaria_ap2_p1.presentation.huacales.list.ListHuacalesUiEvent
import edu.ucne.almamaria_ap2_p1.ui.theme.AlmaMaria_AP2_P1Theme

@Composable
fun HuacalesScreen(
    onDrawer: () -> Unit = {},
    editViewModel: EditHuacalesViewModel = hiltViewModel(),
    listViewModel: ListHucalesViewModel = hiltViewModel()
) {
    var showEdit by remember { mutableStateOf(false) }
    var huacalesIdEdit by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            TopBarComponent(
                title = when {
                    !showEdit -> "Registro de Huacales"
                    huacalesIdEdit == null -> "Crear Huacales"
                    else -> "Editar Huacal"
                },
                onMenuClick = onDrawer
            )
        },
        floatingActionButton = {
            if (!showEdit) {
                FloatingActionButton(
                    onClick = {
                        huacalesIdEdit = null
                        editViewModel.onEvent(EditHuacalesUiEvent.Reset)
                        editViewModel.onEvent(EditHuacalesUiEvent.Load(null))
                        showEdit = true
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Añadir Huacal")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            HuacalesScreenBody(
                showEdit = showEdit,
                editViewModel = editViewModel,
                listViewModel = listViewModel,
                onEditHuacal = { idEntrada ->
                    huacalesIdEdit = idEntrada
                    editViewModel.onEvent(EditHuacalesUiEvent.Reset)
                    editViewModel.onEvent(EditHuacalesUiEvent.Load(idEntrada))
                    showEdit = true
                },
                onCancelEdit = {
                    showEdit = false
                    huacalesIdEdit = null
                    editViewModel.onEvent(EditHuacalesUiEvent.Reset)
                },
                onSaveSuccess = {
                    showEdit = false
                    huacalesIdEdit = null
                    listViewModel.onEvent(ListHuacalesUiEvent.Load)
                    editViewModel.onEvent(EditHuacalesUiEvent.Reset)
                }
            )
        }
    }
}

@Composable
fun HuacalesScreenBody(
    showEdit: Boolean,
    editViewModel: EditHuacalesViewModel,
    listViewModel: ListHucalesViewModel,
    onEditHuacal: (Int) -> Unit = {},
    onCancelEdit: () -> Unit = {},
    onSaveSuccess: () -> Unit = {}
) {
    if (showEdit) {
        EditHuacalesScreen(
            viewModel = editViewModel,
            onCancel = onCancelEdit,
            onSaveSuccess = onSaveSuccess
        )
    } else {
        ListHuacalesScreen(
            viewModel = listViewModel,
            onEditHuacales = onEditHuacal
        )
    }
}

@Preview
@Composable
fun HuacalesScreenPreview() {
    AlmaMaria_AP2_P1Theme {
        HuacalesScreenBody(
            showEdit = false,
            editViewModel = hiltViewModel(),
            listViewModel = hiltViewModel()
        )
    }
}