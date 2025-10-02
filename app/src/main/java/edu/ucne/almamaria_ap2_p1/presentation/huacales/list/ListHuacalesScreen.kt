package edu.ucne.almamaria_ap2_p1.presentation.huacales.list

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.almamaria_ap2_p1.domain.huacales.model.Huacales

@Composable
fun ListHuacalesScreen(
    viewModel: ListHucalesViewModel = hiltViewModel(),
    onEditHuacales: (Int) -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    ListHuacalesBody(
        state = state,
        onEvent = { event ->
            when(event){
                is ListHuacalesUiEvent.Edit -> onEditHuacales(event.id)
                else -> viewModel.onEvent(event)
            }

        }
    )
}

@Composable
fun ListHuacalesBody(
    state: ListHuacalesUiState,
    onEvent: (ListHuacalesUiEvent) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        if(state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("loading")
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .testTag("huacales_list")
        ){
            items(state.huacales){ huacales ->
                HuacalesCard(
                    huacales = huacales,
                    onEdit = {onEvent(ListHuacalesUiEvent.Edit(huacales.idEntrada))},
                    onDelete = {onEvent(ListHuacalesUiEvent.Delete(huacales.idEntrada))}

                )
            }
        }
    }
}
@Composable
fun HuacalesCard(
    huacales: Huacales,
    onEdit: () -> Unit,
    onDelete: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .testTag("huacales_card_${huacales.idEntrada}")
            .clickable{onEdit}
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(modifier = Modifier.weight(1f)){
                Text(
                    text = huacales.nombreCliente,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Fecha: ${huacales.fecha}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Cantidad: ${huacales.cantidad}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Precio: $${huacales.precio}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            TextButton(
                onClick = onEdit,
                modifier = Modifier.testTag("edit_button_${huacales.idEntrada}")
            ) {
                Text("Editar")
            }
            TextButton(
                onClick = onDelete,
                modifier = Modifier.testTag("delete_button_${huacales.idEntrada}")
            ){
                Text("Eliminar")
            }
        }
    }
}

@Preview
@Composable
private fun ListHuacalesBodyPreview(){
    MaterialTheme {
        val state = ListHuacalesUiState()
        ListHuacalesBody(state) { }
    }
}
