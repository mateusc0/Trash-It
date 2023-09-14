package br.com.fiap.trashit.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import br.com.fiap.trashit.view.components.ScreenLabel
import br.com.fiap.trashit.viewmodel.ColetasViewModel

@Composable
fun ColetasScreen(viewModel: ColetasViewModel, navController: NavController) {
        val listaColetas by viewModel.listaColetas.collectAsState()

        ScreenLabel(text = "Histórico")
        LazyColumn(){
                items(listaColetas){
                        Text(text = it.toString())
                }
        }
}

@Composable
fun CardColeta() {
        
}