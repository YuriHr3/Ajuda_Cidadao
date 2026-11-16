package com.example.ajudacidado.domain.repository

import com.example.ajudacidado.domain.model.Demanda

interface DemandaRepository {

    suspend fun registrar(demanda: Demanda)

    suspend fun buscarPorId(id: String): Demanda?

    suspend fun listarTodas(): List<Demanda>
}