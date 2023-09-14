package br.com.fiap.trashit.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.trashit.view.components.ScreenLabel
import br.com.fiap.trashit.viewmodel.LixeiraViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LixeiraScreen(viewModel: LixeiraViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()

    val alert: String;
    val buttonText: String;
    if (uiState.precisaColeta){
        alert= "Coleta Ativa"
        buttonText = "Cancelar"
    } else {
        alert = "Selecione os tipos de resíduos para sua coleta"
        buttonText = "Trash It"
    }



    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ScreenLabel(text = "Lixeira")
        /*Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Text(text = "Lixeira")
        }*/
        Text(text = viewModel.endereco.value.id.toString())
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = alert)
        Text(text = uiState.precisaColeta.toString())
        Spacer(modifier = Modifier.height(40.dp))
        Column {
            LixeiraCheckbox(
                text = "Plástico",
                checkedBoolean = uiState.temPlastico,
                enabledBoolean = uiState.precisaColeta,
                onCheckedFunction = viewModel::updateTemPlastico
            )
            LixeiraCheckbox(
                text = "Papel",
                checkedBoolean = uiState.temPapel,
                enabledBoolean = uiState.precisaColeta,
                onCheckedFunction = viewModel::updateTemPapel
            )
            LixeiraCheckbox(
                text = "Vidro",
                checkedBoolean = uiState.temVidro,
                enabledBoolean = uiState.precisaColeta,
                onCheckedFunction = viewModel::updateTemVidro
            )
            LixeiraCheckbox(
                text = "Metal",
                checkedBoolean = uiState.temMetal,
                enabledBoolean = uiState.precisaColeta,
                onCheckedFunction = viewModel::updateTemMetal
            )
            LixeiraCheckbox(
                text = "Orgânico",
                checkedBoolean = uiState.temOrganico,
                enabledBoolean = uiState.precisaColeta,
                onCheckedFunction = viewModel::updateTemOrganico
            )
            /*Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.temPlastico,
                    enabled = uiState.precisaColeta.not(),
                    onCheckedChange = {
                        viewModel.updateTemPlastico(it)
                    }
                )
                Text(text = "Plástico")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.temPapel,
                    enabled = uiState.precisaColeta.not(),
                    onCheckedChange = {
                        viewModel.updateTemPapel(it)
                    }
                )
                Text(text = "Papel")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.temVidro,
                    enabled = uiState.precisaColeta.not(),
                    onCheckedChange = {
                        viewModel.updateTemVidro(it)
                    }
                )
                Text(text = "Vidro")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.temMetal,
                    enabled = uiState.precisaColeta.not(),
                    onCheckedChange = {
                        viewModel.updateTemMetal(it)
                    }
                )
                Text(text = "Metal")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.temOrganico,
                    enabled = uiState.precisaColeta.not(),
                    onCheckedChange = {
                        viewModel.updateTemOrganico(it)
                    }
                )
                Text(text = "Orgânico")
            }*/
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {
            viewModel.alterarLixeira()

            if (uiState.precisaColeta.not()) {
                GlobalScope.launch {
                    delay(3000)
                    Log.d("FIAPER", "It Worked!!!")
                    viewModel.realizarColeta()
                }
            }


        }) {
            Text(text = buttonText)
        }
        Spacer(modifier = Modifier.height(100.dp))
    }

}

@Composable
fun LixeiraCheckbox(
    text: String,
    checkedBoolean: Boolean,
    enabledBoolean: Boolean,
    onCheckedFunction: (it: Boolean) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkedBoolean,
            enabled = enabledBoolean.not(),
            onCheckedChange = {
                onCheckedFunction(it)
            }
        )
        Text(text = text)
    }

}