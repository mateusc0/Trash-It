package br.com.fiap.trashit.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import br.com.fiap.trashit.model.Coleta
import br.com.fiap.trashit.model.Endereco
import br.com.fiap.trashit.model.Lixeira
import br.com.fiap.trashit.service.database.repository.ColetaRepository
import br.com.fiap.trashit.service.database.repository.EnderecoRepository
import br.com.fiap.trashit.viewmodel.uiState.LixeiraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LixeiraViewModel(context: Context): ViewModel() {
    private val enderecoRepository = EnderecoRepository(context)
    private val coletaRepository = ColetaRepository(context)
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
            uiState.value.temPlastico ||
            uiState.value.temPapel ||
            uiState.value.temMetal ||
            uiState.value.temVidro ||
            uiState.value.temOrganico
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

    fun realizarColeta():Unit {
        if (_uiState.value.precisaColeta){
            val coleta = Coleta(
                id = 0,
                idEnderecoDono = _endereco.value.id,
                lixeira = _endereco.value.lixeira
            )
            coletaRepository.salvar(coleta)
            enderecoRepository.atualizar(
                _endereco.value.copy(lixeira = Lixeira())
            )

            _endereco.update { enderecoRepository.buscarEnderecoPorId(_endereco.value.id) }
            _uiState.update { LixeiraUiState(
                temPlastico = _endereco.value.lixeira.temPlastico,
                temPapel = _endereco.value.lixeira.temPapel,
                temMetal = _endereco.value.lixeira.temMetal,
                temVidro = _endereco.value.lixeira.temVidro,
                temOrganico = _endereco.value.lixeira.temOrganico,
                precisaColeta = _endereco.value.lixeira.precisaColeta
            )}
        }

    }




}