package com.example.ajudacidado.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ajudacidado.domain.repository.DemandaRepository

class DemandaViewModelFactory(
    private val repository: DemandaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(DemandaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DemandaViewModel(repository) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconhecido: ${modelClass.name}"
        )
    }
}