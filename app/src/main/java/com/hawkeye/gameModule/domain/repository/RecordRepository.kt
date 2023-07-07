package com.hawkeye.gameModule.domain.repository

import com.hawkeye.gameModule.core.qqwing.GameDifficulty
import com.hawkeye.gameModule.core.qqwing.GameType
import com.hawkeye.gameModule.data.database.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    suspend fun get(uid: Long): Record
    fun getAll(): Flow<List<Record>>
    fun getAllSortByTime(): Flow<List<Record>>
    fun getAll(difficulty: GameDifficulty, type: GameType): Flow<List<Record>>
    suspend fun insert(record: Record)
    suspend fun delete(record: Record)
}