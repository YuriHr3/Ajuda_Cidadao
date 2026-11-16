package com.example.ajudacidado.domain.usecase

import com.example.ajudacidado.domain.model.Categoria
import com.example.ajudacidado.domain.model.Demanda
import com.example.ajudacidado.domain.model.Prioridade
import com.example.ajudacidado.domain.model.StatusDemanda
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidarDemandaUseCaseTest {

    private val validarDemanda = ValidarDemandaUseCase()

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
    fun deveAceitarDemandaValida() {
        assertTrue(validarDemanda(criarDemanda()))
    }

    @Test
    fun deveRejeitarDemandaSemTitulo() {
        assertFalse(validarDemanda(criarDemanda(titulo = "")))
    }

    @Test
    fun deveRejeitarDemandaSemDescricao() {
        assertFalse(validarDemanda(criarDemanda(descricao = "")))
    }

    @Test
    fun deveRejeitarDemandaComTituloEmBranco() {
        assertFalse(validarDemanda(criarDemanda(titulo = "   ")))
    }
}
