package edu.ucne.almamaria_ap2_p1.presentation.huacales.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditHuacalesScreen(
    viewModel: EditHuacalesViewModel = hiltViewModel(),
    onCancel: () -> Unit = {},
    onSaveSuccess: () -> Unit = {}
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved) {
        if (state.saved) {
            onSaveSuccess()
        }
    }

    EditHuacalesBody(
        state = state,
        onEvent = viewModel::onEvent,
        onCancel = onCancel
    )
}

@Composable
fun EditHuacalesBody(
    state: EditHuacalesUiState,
    onEvent:(EditHuacalesUiEvent) -> Unit,
    onCancel: () -> Unit = {}
){
    Column(
        modifier = Modifier
            .padding(16.dp)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Fecha: ${state.fecha}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.nombreCliente,
            onValueChange = {onEvent(EditHuacalesUiEvent.NombreChanged(it))},
            label = {Text("Nombre: ")},
            isError = state.nombreError != null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_nombre")
        )
        if(state.nombreError != null){
            Text(
                text = state.nombreError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.cantidad,
            onValueChange = {onEvent(EditHuacalesUiEvent.CantidadChanged(it))},
            label = {Text("Cantidad: ")},
            isError = state.cantidadError != null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_cantidad")
        )
        if(state.cantidadError != null){
            Text(
                text = state.cantidadError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.precio,
            onValueChange = {onEvent(EditHuacalesUiEvent.PrecioChanged(it))},
            label = {Text("Precio: ")},
            isError = state.precioError != null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input_precio")
        )
        if(state.precioError != null){
            Text(
                text = state.precioError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = onCancel,
                enabled = !state.isSaving
            ) {
                Text("Cancelar")
            }
            Button(
                onClick = { onEvent(EditHuacalesUiEvent.Save) },
                enabled = !state.isSaving,
                modifier = Modifier.testTag("btn_guardar")
            ) {
                Text("Guardar")
            }
        }

        if (!state.isNew) {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = { onEvent(EditHuacalesUiEvent.Delete) },
                enabled = !state.isDeleting,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Eliminar")
            }
        }
    }
}

@Preview
@Composable
private fun EditHuacalesBodyPreview(){
    val state = EditHuacalesUiState()
    MaterialTheme {
        EditHuacalesBody(state = state, onEvent = {}) { }
    }
}