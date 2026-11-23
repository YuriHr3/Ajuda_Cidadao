package com.example.ajudacidado.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajudacidado.domain.model.Demanda
import com.example.ajudacidado.domain.repository.DemandaRepository
import com.example.ajudacidado.domain.usecase.RegistrarDemandaUseCase
import com.example.ajudacidado.domain.usecase.ValidarDemandaUseCase
import kotlinx.coroutines.launch

class DemandaViewModel(
    private val repository: DemandaRepository
) : ViewModel() {

    private val registrarDemandaUseCase =
        RegistrarDemandaUseCase(
            repository,
            ValidarDemandaUseCase()
        )

    fun registrarDemanda(
        demanda: Demanda,
        onResultado: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val resultado = registrarDemandaUseCase(demanda)
            onResultado(resultado)
        }
    }

    fun listarDemandas(
        onResultado: (List<Demanda>) -> Unit
    ) {
        viewModelScope.launch {
            onResultado(repository.listarTodas())
        }
    }
}