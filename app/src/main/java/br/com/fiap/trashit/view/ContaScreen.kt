package br.com.fiap.trashit.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.trashit.R
import br.com.fiap.trashit.view.components.ScreenLabel
import br.com.fiap.trashit.view.components.UserInputTextField
import br.com.fiap.trashit.viewmodel.ContaViewModel

@Composable
fun ContaScreen(viewModel: ContaViewModel, navController: NavController) {
        val conta by viewModel.usuario.collectAsState()
        val endereco by viewModel.endereco.collectAsState()

        Column(modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.shady_grey))
                .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally) {
                ScreenLabel(text = "Conta", painterResource(id = R.drawable.baseline_person_2_24))
                UserInputTextField(
                        text = "Nome Completo",
                        value = conta.nomeCompleto,
                        onCheckedFunction = {},
                        visualTransformation = VisualTransformation.None,
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                UserInputTextField(
                        text = "CPF",
                        value = conta.cpf,
                        onCheckedFunction = {},
                        visualTransformation = VisualTransformation.None,
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                UserInputTextField(
                        text = "Email",
                        value = conta.email,
                        onCheckedFunction = viewModel::updateEmail,
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        enabled = true,
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                UserInputTextField(
                        text = "Celular",
                        value = conta.celular,
                        onCheckedFunction = viewModel::updateCelular,
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        enabled = true,
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                UserInputTextField(
                        text = "Senha",
                        value = conta.senha,
                        onCheckedFunction = viewModel::updateSenha,
                        visualTransformation = PasswordVisualTransformation(),
                        KeyboardOptions(keyboardType = KeyboardType.Password),
                        enabled = true,
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                UserInputTextField(
                        text = "CEP",
                        value = endereco.cep,
                        onCheckedFunction = {},
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                /*UserInput(label = "Nome Completo", placeHolder = "Nome Usuário", value = "", modifier = Modifier.fillMaxWidth())
                UserInput(label = "CPF", placeHolder = "000.000.000-00", value = "")
                UserInput(label = "Email", placeHolder = "exemplo@gmail.com", value = "")
                UserInput(label = "Celular", placeHolder = "(00)00000-0000", value = "")
                UserInput(label = "Senha", placeHolder = "**********", value = "")
                UserInput(label = "CEP", placeHolder = "00000-000", value = "")*/
                Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                ) {
                        /*UserInput(
                                label = "Nº Residêcia",
                                placeHolder = conta.isLogged.toString(),
                                value = "",
                                modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        UserInput(
                                label = "Endereço",
                                placeHolder = "Rua exemplo, Bairro Exemplo",
                                value = "",
                                modifier = Modifier.weight(2f)
                        )*/
                        UserInputTextField(
                                text = "Número Casa",
                                value = endereco.numero,
                                onCheckedFunction = {},
                                visualTransformation = VisualTransformation.None,
                                modifier = Modifier
                                        .weight(1F)
                                        .padding(
                                                start = 10.dp,
                                                end = 5.dp
                                        )
                        )
                        UserInputTextField(
                                text = "Endereco",
                                value = "${endereco.rua}, ${endereco.bairro}",
                                onCheckedFunction = {},
                                visualTransformation = VisualTransformation.None,
                                modifier = Modifier
                                        .weight(2F)
                                        .padding(end = 10.dp)
                        )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row{
                        /*Button(
                                shape = RoundedCornerShape(24.dp),
                                onClick = { viewModel.updateUsuario() },
                                colors = ButtonDefaults
                                                .textButtonColors(containerColor =
                                                        colorResource(id = R.color.TrashItGreen)
                                                )
                        ) {
                                Text(
                                        text = "Salvar",
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        modifier = Modifier.padding(
                                                horizontal = 20.dp,
                                                vertical = 10.dp
                                        )
                                )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(onClick = {
                                viewModel.logout()
                                navController.navigate("login")
                        }) {
                                Text(text = "Sair")
                        }*/
                        SpecialButtons(
                                text = "Salvar",
                                color = colorResource(id = R.color.trashIt_green),
                                modifier = Modifier.width(140.dp)
                        ) {
                                viewModel.updateUsuario()
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        SpecialButtons(
                                text = "Sair",
                                color = colorResource(id = R.color.trashIt_green),
                                modifier = Modifier.width(140.dp)
                        ) {
                                viewModel.logout()
                                navController.navigate("login")
                        }
                }
                Spacer(modifier = Modifier.height(10.dp))
                SpecialButtons(
                        text = "Deletar Conta",
                        enabled = false,
                        color = colorResource(id = R.color.disabled_red),
                        modifier = Modifier.width(300.dp)
                ) {}
                Spacer(modifier = Modifier.height(100.dp))
        }
}

@Composable
fun SpecialButtons(
        text: String,
        color: Color,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        onCLickFunction: () -> Unit
) {
        Button(
                shape = RoundedCornerShape(24.dp),
                onClick = onCLickFunction,
                colors = ButtonDefaults
                        .textButtonColors(
                                containerColor = color,
                                disabledContainerColor = color,
                                disabledContentColor = Color.LightGray
                        ),
                enabled = enabled,
                modifier = modifier
        ) {
                Text(
                        text = text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(
                                vertical = 10.dp
                        )
                )
        }
}
