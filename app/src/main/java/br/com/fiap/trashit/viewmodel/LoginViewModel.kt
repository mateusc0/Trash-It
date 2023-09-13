package br.com.fiap.trashit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

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
}