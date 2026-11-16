
package com.example.ajudacidado.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ModelosTest {

    @Test
    fun devePossuirQuatroNiveisDePrioridade() {
        assertEquals(4, Prioridade.values().size)
    }

    @Test
    fun devePossuirNoveCategorias() {
        assertEquals(9, Categoria.values().size)
    }

    @Test
    fun devePossuirCincoStatusDeDemanda() {
        assertEquals(5, StatusDemanda.values().size)
    }

    @Test
    fun deveCriarDemandaComDadosCorretos() {
        val demanda = Demanda(
            id = "123",
            titulo = "Buraco na rua",
            descricao = "Buraco grande próximo à escola",
            categoria = Categoria.INFRAESTRUTURA,
            prioridade = Prioridade.ALTA,
            status = StatusDemanda.RECEBIDA,
            localizacao = "Rua Central",
            dataCriacao = 1763300000000L
        )

        assertEquals("123", demanda.id)
        assertEquals("Buraco na rua", demanda.titulo)
        assertEquals(Prioridade.ALTA, demanda.prioridade)
        assertEquals(StatusDemanda.RECEBIDA, demanda.status)
        assertEquals("Rua Central", demanda.localizacao)
    }

    @Test
    fun devePermitirDemandaSemLocalizacao() {
        val demanda = Demanda(
            id = "124",
            titulo = "Falta de iluminação",
            descricao = "Poste apagado",
            categoria = Categoria.ILUMINACAO_PUBLICA,
            prioridade = Prioridade.MEDIA,
            status = StatusDemanda.RECEBIDA,
            localizacao = null,
            dataCriacao = 1763300000000L
        )

        assertEquals(null, demanda.localizacao)
    }
}
