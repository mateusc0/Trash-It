package br.com.fiap.trashit.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.trashit.model.Endereco
import br.com.fiap.trashit.model.Lixeira
import br.com.fiap.trashit.service.database.dao.ColetaDao
import br.com.fiap.trashit.service.database.repository.ColetaRepository
import br.com.fiap.trashit.service.database.repository.EnderecoRepository
import br.com.fiap.trashit.viewmodel.uiState.LixeiraUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LixeiraViewModel(context: Context): ViewModel() {

    private val enderecoRepository = EnderecoRepository(context)

    private var _endereco = MutableStateFlow<Endereco>(enderecoRepository.buscarEnderecoPorId(1))
    val endereco: StateFlow<Endereco>
        get() = _endereco

    private val _uiState = MutableStateFlow<LixeiraUiState>(LixeiraUiState(
        temPlastico = _endereco.value.lixeira.temPlastico,
        temPapel = _endereco.value.lixeira.temPapel,
        temMetal = _endereco.value.lixeira.temMetal,
        temVidro = _endereco.value.lixeira.temVidro,
        temOrganico = _endereco.value.lixeira.temOrganico,
        precisaColeta = _endereco.value.lixeira.precisaColeta
    ))
    val uiState: StateFlow<LixeiraUiState>
        get() = _uiState

    /*var temPlastico by mutableStateOf(endereco.value.lixeira.temPlastico)
    var temPapel by mutableStateOf(endereco.value.lixeira.temPapel)
    var temMetal by mutableStateOf(endereco.value.lixeira.temMetal)
    var temVidro by mutableStateOf(endereco.value.lixeira.temVidro)
    var temOrganico by mutableStateOf(endereco.value.lixeira.temOrganico)
    var precisaColeta by mutableStateOf(endereco.value.lixeira.precisaColeta)*/



    fun alterarLixeira(): Unit {


        val enderecoAtt = _endereco.value.copy(
            lixeira = Lixeira(
            precisaColeta = (uiState.value.precisaColeta).not(),
            temPlastico = uiState.value.temPlastico,
            temPapel = uiState.value.temPapel,
            temMetal = uiState.value.temMetal,
            temVidro = uiState.value.temVidro,
            temOrganico = uiState.value.temOrganico

        ))


           /* Endereco(
            id = endereco.value.id,
            numero = endereco.value.numero,
            complemento = endereco.value.complemento,
            bairro = endereco.value.bairro,
            cep = endereco.value.cep,
            rua = endereco.value.rua,
            cidade = endereco.value.cidade,
            uf = endereco.value.uf,
            lixeira = Lixeira(
                precisaColeta = (uiState.value.precisaColeta).not(),
                temPlastico = uiState.value.temPlastico,
                temPapel = uiState.value.temPapel,
                temMetal = uiState.value.temMetal,
                temVidro = uiState.value.temVidro,
                temOrganico = uiState.value.temOrganico
            ))*/
        if (
            uiState.value.temPlastico.equals(true) ||
            uiState.value.temPapel.equals(true) ||
            uiState.value.temMetal.equals(true) ||
            uiState.value.temVidro.equals(true) ||
            uiState.value.temOrganico.equals(true)
            ) {
            enderecoRepository.atualizar(enderecoAtt)
            _endereco.update {
                enderecoRepository.buscarEnderecoPorId(1) }

            _uiState.update { LixeiraUiState(
                temPlastico = _endereco.value.lixeira.temPlastico,
                temPapel = _endereco.value.lixeira.temPapel,
                temMetal = _endereco.value.lixeira.temMetal,
                temVidro = _endereco.value.lixeira.temVidro,
                temOrganico = _endereco.value.lixeira.temOrganico,
                precisaColeta = _endereco.value.lixeira.precisaColeta
            ) }
        }

    }

    fun updateTemPlastico(value: Boolean):Unit {
        _uiState.update { currentState -> currentState.copy(
            temPlastico = value
        ) }
    }
    fun updateTemPapel(value: Boolean):Unit {
        _uiState.update { currentState -> currentState.copy(
            temPapel = value
        ) }
    }
    fun updateTemMetal(value: Boolean):Unit {
        _uiState.update { currentState -> currentState.copy(
            temMetal = value
        ) }
    }
    fun updateTemVidro(value: Boolean):Unit {
        _uiState.update { currentState -> currentState.copy(
            temVidro = value
        ) }
    }
    fun updateTemOrganico(value: Boolean):Unit {
        _uiState.update { currentState -> currentState.copy(
            temOrganico = value
        ) }
    }




}