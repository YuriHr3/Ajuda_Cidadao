package com.example.ajudacidado.domain.usecase

import com.example.ajudacidado.domain.model.Demanda

class ValidarDemandaUseCase {

    operator fun invoke(demanda: Demanda): Boolean {
        return demanda.titulo.isNotBlank() &&
                demanda.descricao.isNotBlank()
    }
}