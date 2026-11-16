package com.example.ajudacidado.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "demandas")
data class DemandaEntity(
    @PrimaryKey
    val id: String,

    val titulo: String,
    val descricao: String,
    val categoria: String,
    val prioridade: String,
    val status: String,
    val localizacao: String?,
    val dataCriacao: Long
)