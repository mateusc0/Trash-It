package br.com.fiap.trashit.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.fiap.trashit.viewmodel.ColetasViewModel

@Composable
fun ColetasScreen(viewModel: ColetasViewModel, navController: NavController) {
        Text(text = "Histórico")
        LazyColumn(){
                /*items(){
                        Text(text = it)
                }*/
        }
}

@Composable
fun CardColeta() {
        
}