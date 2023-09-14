package br.com.fiap.trashit.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import br.com.fiap.trashit.service.database.repository.ColetaRepository

class ColetasViewModel(context: Context): ViewModel() {
    val coletaRepository = ColetaRepository(context)



}