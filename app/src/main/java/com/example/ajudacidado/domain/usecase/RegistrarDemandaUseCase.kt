package com.example.ajudacidado.domain.usecase

import com.example.ajudacidado.domain.model.Demanda
import com.example.ajudacidado.domain.repository.DemandaRepository

class RegistrarDemandaUseCase(
    private val repository: DemandaRepository,
    private val validarDemanda: ValidarDemandaUseCase
) {

    suspend operator fun invoke(demanda: Demanda): Boolean {
        if (!validarDemanda(demanda)) {
            return false
        }

        repository.registrar(demanda)
        return true
    }
}