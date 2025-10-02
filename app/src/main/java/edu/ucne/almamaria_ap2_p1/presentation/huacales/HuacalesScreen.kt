package edu.ucne.almamaria_ap2_p1.presentation.huacales

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.almamaria_ap2_p1.presentation.huacales.edit.EditHuacalesViewModel

@Composable
fun HuacalesScreen(
  onDrawer: () -> Unit ={},
  editViewModel: EditHuacalesViewModel = hiltViewModel(),
  listViewModel: EditHuacalesViewModel = hiltViewModel()
){
    var showEdit by remember { mutableStateOf(false) }
    var huacalesIdEdit by remember{mutableStateOf<Int?>(null)}

}
