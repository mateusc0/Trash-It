package br.com.fiap.trashit.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import br.com.fiap.trashit.model.Endereco
import br.com.fiap.trashit.model.Usuario
import br.com.fiap.trashit.service.database.repository.EnderecoRepository
import br.com.fiap.trashit.service.database.repository.UsuarioRepository
import br.com.fiap.trashit.view.components.trashItToast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ContaViewModel(val context: Context): ViewModel() {
    private val enderecoRepository = EnderecoRepository(context)
    private val usuarioRepository = UsuarioRepository(context)

    private var _usuario = MutableStateFlow<Usuario>(usuarioRepository.buscarUsuarioPorId(1))
    val usuario: StateFlow<Usuario>
        get() = _usuario

    private var _endereco = MutableStateFlow<Endereco>(enderecoRepository.buscarEnderecoPorId(1))
    val endereco: StateFlow<Endereco>
        get() = _endereco

    fun updateEmail(email: String):Unit {
        _usuario.update { currentState ->
            currentState.copy(
                email = email
            )
        }
    }
    fun updateCelular(celular: String):Unit {
        _usuario.update { currentState ->
            currentState.copy(
                celular = celular
            )
        }
    }
    fun updateSenha(senha: String):Unit {
        _usuario.update { currentState ->
            currentState.copy(
                senha = senha
            )
        }
    }

    fun logout():Unit {
        _usuario.update { currentState ->
            currentState.copy(
                isLogged = false
            )
        }
        usuarioRepository.atualizar(_usuario.value)
    }

    fun updateUsuario():Unit {
        if (_usuario.value != usuarioRepository.buscarUsuarioPorId(1)) {
                usuarioRepository.atualizar(_usuario.value)
                _usuario.update {
                    usuarioRepository.buscarUsuarioPorId(1)
                }
                trashItToast(text = "Usuário atualizado", context = context)
        } else {
            trashItToast(text = "Altere alguma informação", context= context )
        }
    }


}