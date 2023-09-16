package br.com.fiap.trashit.view

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.trashit.R
import br.com.fiap.trashit.model.Lixeira
import br.com.fiap.trashit.view.components.ScreenLabel
import br.com.fiap.trashit.viewmodel.ColetasViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun ColetasScreen(viewModel: ColetasViewModel, navController: NavController) {
        val listaColetas by viewModel.listaColetas.collectAsState()
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

        Box(modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.ShadeGrey))
        ) {
                if (listaColetas.isEmpty()){
                        Text(
                                text = "Nenhuma coleta realizada até o momento",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(alignment = Alignment.Center)
                        )
                } else {
                        LazyColumn(
                                modifier = Modifier.padding(
                                        bottom = 80.dp,
                                        start = 2.dp,
                                        end = 2.dp
                                )
                        ) {
                                item { Spacer(modifier = Modifier.height(55.dp)) }

                                items(listaColetas) {
                                        CardColeta(
                                                idColeta = it.id,
                                                dataColeta = it.dtColeta,
                                                lixeira = it.lixeira,
                                                simpleDateFormat = simpleDateFormat
                                        )
                                }

                                item { Spacer(modifier = Modifier.height(20.dp)) }
                        }
                }
                
                ScreenLabel(text = "Histórico", painterResource(id = R.drawable.baseline_access_time_24))

        }
}

@Composable
fun CardColeta(
        idColeta: Long,
        dataColeta: Date,
        lixeira: Lixeira,
        simpleDateFormat: SimpleDateFormat,
) {
        val dataFormatada = simpleDateFormat.format(dataColeta)
        Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(start = 4.dp, end = 4.dp, bottom = 10.dp)) {
             Column(
                     modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
                     verticalArrangement = Arrangement.SpaceBetween
             ) {
                     Row(
                             modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 10.dp),
                             horizontalArrangement = Arrangement.SpaceBetween,
                             verticalAlignment = Alignment.CenterVertically
                     ) {
                             Text(text = "Coleta Realizada - $dataFormatada", fontSize = 20.sp)
                             Icon(
                                     imageVector = Icons.Rounded.Check,
                                     contentDescription = null,
                                     tint = colorResource(id = R.color.TrashItGreen)
                             )
                     }
                     Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)) {
                             MaterialLabel(
                                     materialBoolean = lixeira.temPlastico,
                                     materialColor = colorResource(id = R.color.PlasticRed),
                                     materialName = "Plástico"
                             )
                             MaterialLabel(
                                     materialBoolean = lixeira.temPapel,
                                     materialColor = colorResource(id = R.color.PaperBlue),
                                     materialName = "Papel"
                             )
                             MaterialLabel(
                                     materialBoolean = lixeira.temVidro,
                                     materialColor = colorResource(id = R.color.VitroGreen),
                                     materialName = "Vidro"
                             )
                             MaterialLabel(
                                     materialBoolean = lixeira.temMetal,
                                     materialColor = colorResource(id = R.color.MetalYellow),
                                     materialName = "Metal"
                             )
                             MaterialLabel(
                                     materialBoolean = lixeira.temOrganico,
                                     materialColor = colorResource(id = R.color.OrganicOrange),
                                     materialName = "Orgânico"
                             )


                     }
             }   
        }
}

@Composable
fun MaterialLabel(materialBoolean: Boolean, materialColor: Color, materialName: String) {
        if (materialBoolean){
                Box(modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(materialColor)
                ){
                        Text(
                                text = materialName,
                                fontSize = 17.sp,
                                color = Color.White,
                                modifier = Modifier.padding(
                                vertical = 2.dp,
                                horizontal = 8.dp
                        ))
                }
        }
}