package br.com.fiap.trashit.view

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.trashit.model.Endereco
import br.com.fiap.trashit.model.Lixeira
import br.com.fiap.trashit.service.database.repository.EnderecoRepository
import br.com.fiap.trashit.viewmodel.LoginViewModel

@Composable
fun LoginScreen(context: Context,viewModel: LoginViewModel, navController: NavController) {
        val enderecoRepository = EnderecoRepository(context)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Text(fontSize = 48.sp,text = "TrashIt")
            }
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                label = { Text(text = "Email") },
                value = viewModel.email,
                onValueChange = {viewModel.updateEmail(it)}
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(text = "Senha") },
                value = viewModel.password,
                onValueChange = {viewModel.updatePassword(it)},
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(onClick = {
                if (enderecoRepository.listarEnderecos().isEmpty()) {
                    val end1 = Endereco(
                        0, "09211111", "125", "Rua Exemplo", "",
                        "Bairro Exemplo", "Cidade Exemplo", "Estado Exemplo", Lixeira()
                    )
                    enderecoRepository.salvar(end1)
                }

                navController.navigate("lixeira") }) {
                Text(text = "Entrar", fontSize = 16.sp)
            }
        }
}
