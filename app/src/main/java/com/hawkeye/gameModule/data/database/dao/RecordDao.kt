package com.hawkeye.gameModule.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hawkeye.gameModule.core.qqwing.GameDifficulty
import com.hawkeye.gameModule.core.qqwing.GameType
import com.hawkeye.gameModule.data.database.model.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM record WHERE board_uid == :uid")
    suspend fun get(uid: Long): Record

    @Query("SELECT * FROM record")
    fun getAll(): Flow<List<Record>>

    @Query(
        "SELECT * FROM record " +
                "WHERE type == :type and difficulty == :difficulty " +
                "ORDER BY time ASC"
    )
    fun getAll(difficulty: GameDifficulty, type: GameType): Flow<List<Record>>

    @Query("SELECT * FROM record ORDER BY time ASC")
    fun getAllSortByTime(): Flow<List<Record>>

    @Delete
    suspend fun delete(record: Record)

    @Insert
    suspend fun insert(record: Record)
}