
package com.example.ajudacidado.domain.usecase

import com.example.ajudacidado.domain.model.*
import com.example.ajudacidado.domain.repository.DemandaRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class RegistrarDemandaUseCaseTest {

    private class FakeDemandaRepository : DemandaRepository {
        val demandas = mutableListOf<Demanda>()

        override suspend fun registrar(demanda: Demanda) {
            demandas.add(demanda)
        }

        override suspend fun buscarPorId(id: String): Demanda? {
            return demandas.find { it.id == id }
        }

        override suspend fun listarTodas(): List<Demanda> {
            return demandas.toList()
        }
    }

    private fun criarDemanda(
        titulo: String = "Buraco na rua",
        descricao: String = "Buraco próximo à escola"
    ) = Demanda(
        id = "123",
        titulo = titulo,
        descricao = descricao,
        categoria = Categoria.INFRAESTRUTURA,
        prioridade = Prioridade.ALTA,
        status = StatusDemanda.RECEBIDA,
        localizacao = null,
        dataCriacao = 1763300000000L
    )

    @Test
    fun deveRegistrarDemandaValida() = runBlocking {
        val repository = FakeDemandaRepository()
        val useCase = RegistrarDemandaUseCase(
            repository,
            ValidarDemandaUseCase()
        )

        val resultado = useCase(criarDemanda())

        assertTrue(resultado)
        assertEquals(1, repository.demandas.size)
    }

    @Test
    fun naoDeveRegistrarDemandaSemTitulo() = runBlocking {
        val repository = FakeDemandaRepository()
        val useCase = RegistrarDemandaUseCase(
            repository,
            ValidarDemandaUseCase()
        )

        val resultado = useCase(criarDemanda(titulo = ""))

        assertFalse(resultado)
        assertEquals(0, repository.demandas.size)
    }

    @Test
    fun naoDeveRegistrarDemandaSemDescricao() = runBlocking {
        val repository = FakeDemandaRepository()
        val useCase = RegistrarDemandaUseCase(
            repository,
            ValidarDemandaUseCase()
        )

        val resultado = useCase(criarDemanda(descricao = ""))

        assertFalse(resultado)
        assertEquals(0, repository.demandas.size)
    }
}
