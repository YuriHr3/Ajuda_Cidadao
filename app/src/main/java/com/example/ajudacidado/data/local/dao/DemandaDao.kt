package com.example.ajudacidado.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ajudacidado.data.local.entity.DemandaEntity

@Dao
interface DemandaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(demanda: DemandaEntity)

    @Query("SELECT * FROM demandas WHERE id = :id")
    suspend fun buscarPorId(id: String): DemandaEntity?

    @Query("SELECT * FROM demandas ORDER BY dataCriacao DESC")
    suspend fun listarTodas(): List<DemandaEntity>

    @Query("DELETE FROM demandas WHERE id = :id")
    suspend fun excluirPorId(id: String)
}