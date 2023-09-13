package br.com.fiap.trashit.view

import android.graphics.drawable.Icon
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.com.fiap.trashit.viewmodel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
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
            OutlinedButton(onClick = { navController.navigate("lixeira") }) {
                Text(text = "Entrar", fontSize = 16.sp)
            }
        }
}
