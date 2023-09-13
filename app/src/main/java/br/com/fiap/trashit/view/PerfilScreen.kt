package br.com.fiap.trashit.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PerfilScreen(navController: NavController) {
        Column(modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
                .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                        Text(text = "Nome Usuário")
                }
                UserInput(label = "Nome Completo", placeHolder = "Nome Usuário", value = "", modifier = Modifier.fillMaxWidth())
                UserInput(label = "CPF", placeHolder = "000.000.000-00", value = "")
                UserInput(label = "Email", placeHolder = "exemplo@gmail.com", value = "")
                UserInput(label = "Celular", placeHolder = "(00)00000-0000", value = "")
                UserInput(label = "Senha", placeHolder = "**********", value = "")
                UserInput(label = "CEP", placeHolder = "00000-000", value = "")
                Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                ) {
                        UserInput(
                                label = "Nº Residêcia",
                                placeHolder = "123",
                                value = "",
                                modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        UserInput(
                                label = "Endereço",
                                placeHolder = "Rua exemplo, Bairro Exemplo",
                                value = "",
                                modifier = Modifier.weight(2f)
                        )
                }
                Row(modifier = Modifier.padding(4.dp)) {
                        Button(onClick = { /*TODO*/ }) {
                                Text(text = "Sair")
                        }
                        Button(onClick = { /*TODO*/ }) {
                                Text(text = "Deletar")
                        }
                }
                Spacer(modifier = Modifier.height(80.dp))



        }

}

@Composable
fun UserInput(label: String, placeHolder: String, value: String, modifier: Modifier = Modifier) {
        Column(modifier = modifier) {
                Text(text = label)
                OutlinedTextField(
                        value = placeHolder,
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth()
                )
        }
}
