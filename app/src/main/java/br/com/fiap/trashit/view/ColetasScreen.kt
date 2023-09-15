package br.com.fiap.trashit.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.trashit.R
import br.com.fiap.trashit.model.Lixeira
import br.com.fiap.trashit.view.components.ScreenLabel
import br.com.fiap.trashit.viewmodel.ColetasViewModel
import java.util.Date

@Composable
fun ColetasScreen(viewModel: ColetasViewModel, navController: NavController) {
        val listaColetas by viewModel.listaColetas.collectAsState()

        ScreenLabel(text = "Histórico")
        LazyColumn(){
                items(listaColetas){
                        CardColeta(
                                idColeta = it.id,
                                dataColeta = it.dtColeta,
                                lixeira = it.lixeira
                        )
                }
        }
}

@Composable
fun CardColeta(idColeta: Long, dataColeta: Date, lixeira: Lixeira) {
        Card() {
             Column {
                     Row {
                             Text(text = "Coleta Realizada - $dataColeta")
                     }
                     Row {
                             MaterialLabel(
                                     materialBoolean = lixeira.temPlastico,
                                     materialColor = colorResource(id = R.color.PlasticRed)
                             )
                             MaterialLabel(
                                     materialBoolean = lixeira.temPapel,
                                     materialColor = colorResource(id = R.color.PaperBlue)
                             )
                             MaterialLabel(
                                     materialBoolean = lixeira.temVidro,
                                     materialColor = colorResource(id = R.color.VitroGreen)
                             )
                             MaterialLabel(
                                     materialBoolean = lixeira.temMetal,
                                     materialColor = colorResource(id = R.color.MetalYellow)
                             )
                             MaterialLabel(
                                     materialBoolean = lixeira.temOrganico,
                                     materialColor = colorResource(id = R.color.OrganicOrange)
                             )


                     }
             }   
        }
}

@Composable
fun MaterialLabel(materialBoolean: Boolean, materialColor: Color) {
        if (materialBoolean){
                Box(modifier = Modifier
                        .size(width = 40.dp, height = 10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(materialColor)
                )
        }
}