package com.example.ajudacidado.domain.model

data class Demanda(
    val id: String,
    val titulo: String,
    val descricao: String,
    val categoria: Categoria,
    val prioridade: Prioridade,
    val status: StatusDemanda,
    val localizacao: String?,
    val dataCriacao: Long
)