package br.com.fiap.trashit.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.fiap.trashit.model.Endereco
import br.com.fiap.trashit.model.Lixeira
import br.com.fiap.trashit.service.database.repository.EnderecoRepository

class LoginViewModel(context: Context): ViewModel() {
    val enderecoRepository = EnderecoRepository(context)

    var email by mutableStateOf("exemploEmail@gmail.com")
        private set
    var password by mutableStateOf("example")
        private set

    fun updateEmail(emailInput: String) {
        email = emailInput
    }
    fun updatePassword(passwordInput: String) {
        password = passwordInput
    }

    fun login(){
        if (enderecoRepository.listarEnderecos().isEmpty()) {
            val end = Endereco(
                0, "09211111", "125", "Rua Exemplo", "",
                "Bairro Exemplo", "Cidade Exemplo", "Estado Exemplo", Lixeira()
            )
            enderecoRepository.salvar(end)
        }
    }
}