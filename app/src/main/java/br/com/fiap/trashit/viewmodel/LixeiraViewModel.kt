package br.com.fiap.trashit.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import br.com.fiap.trashit.model.Endereco
import br.com.fiap.trashit.model.Lixeira
import br.com.fiap.trashit.service.database.dao.ColetaDao
import br.com.fiap.trashit.service.database.repository.ColetaRepository
import br.com.fiap.trashit.service.database.repository.EnderecoRepository

class LixeiraViewModel(context: Context): ViewModel() {

    val enderecoRepository = EnderecoRepository(context)

    var endereco by mutableStateOf<Endereco>(enderecoRepository.buscarEnderecoPorId(1))

    var temPlastico by mutableStateOf(endereco.lixeira.temPlastico)
    var temPapel by mutableStateOf(endereco.lixeira.temPapel)
    var temMetal by mutableStateOf(endereco.lixeira.temMetal)
    var temVidro by mutableStateOf(endereco.lixeira.temVidro)
    var temOrganico by mutableStateOf(endereco.lixeira.temOrganico)



    fun alterarLixeira(): Unit {
        val enderecoAtt = Endereco(
            id = endereco.id,
            numero = endereco.numero,
            complemento = endereco.complemento,
            bairro = endereco.bairro,
            cep = endereco.cep,
            rua = endereco.rua,
            cidade = endereco.cidade,
            uf = endereco.uf,
            lixeira = Lixeira(
                precisaColeta = (endereco.lixeira.precisaColeta).not(),
                temPlastico = temPlastico,
                temPapel = temPapel,
                temMetal = temMetal,
                temVidro = temVidro,
                temOrganico = temOrganico
            )

        )
        enderecoRepository.atualizar(enderecoAtt)
        endereco = enderecoRepository.buscarEnderecoPorId(1)

    }




}