package com.example.ajudacidado.data.repository

import com.example.ajudacidado.data.local.dao.DemandaDao
import com.example.ajudacidado.data.local.entity.DemandaEntity
import com.example.ajudacidado.domain.model.*
import com.example.ajudacidado.domain.repository.DemandaRepository

class DemandaRepositoryImpl(
    private val demandaDao: DemandaDao
) : DemandaRepository {

    override suspend fun registrar(demanda: Demanda) {
        demandaDao.inserir(
            DemandaEntity(
                id = demanda.id,
                titulo = demanda.titulo,
                descricao = demanda.descricao,
                categoria = demanda.categoria.name,
                prioridade = demanda.prioridade.name,
                status = demanda.status.name,
                localizacao = demanda.localizacao,
                dataCriacao = demanda.dataCriacao
            )
        )
    }

    override suspend fun buscarPorId(id: String): Demanda? {
        return demandaDao.buscarPorId(id)?.toDomain()
    }

    override suspend fun listarTodas(): List<Demanda> {
        return demandaDao.listarTodas().map { it.toDomain() }
    }

    private fun DemandaEntity.toDomain(): Demanda {
        return Demanda(
            id = id,
            titulo = titulo,
            descricao = descricao,
            categoria = Categoria.valueOf(categoria),
            prioridade = Prioridade.valueOf(prioridade),
            status = StatusDemanda.valueOf(status),
            localizacao = localizacao,
            dataCriacao = dataCriacao
        )
    }
}