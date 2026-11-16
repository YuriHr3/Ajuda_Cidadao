package com.example.ajudacidado.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ajudacidado.data.local.dao.DemandaDao
import com.example.ajudacidado.data.local.entity.DemandaEntity

@Database(
    entities = [DemandaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun demandaDao(): DemandaDao
}