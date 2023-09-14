package br.com.fiap.trashit.viewmodel.uiState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class LixeiraUiState(
    var temPlastico: Boolean,
    var temPapel: Boolean,
    var temMetal: Boolean,
    var temVidro: Boolean,
    var temOrganico: Boolean,
    var precisaColeta: Boolean
)