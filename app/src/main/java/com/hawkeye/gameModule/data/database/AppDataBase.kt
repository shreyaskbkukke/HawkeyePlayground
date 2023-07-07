package com.hawkeye.gameModule.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hawkeye.gameModule.data.database.converters.DurationConverter
import com.hawkeye.gameModule.data.database.converters.GameDifficultyConverter
import com.hawkeye.gameModule.data.database.converters.GameTypeConverter
import com.hawkeye.gameModule.data.database.converters.ZonedDateTimeConverter
import com.hawkeye.gameModule.data.database.dao.BoardDao
import com.hawkeye.gameModule.data.database.dao.FolderDao
import com.hawkeye.gameModule.data.database.dao.RecordDao
import com.hawkeye.gameModule.data.database.dao.SavedGameDao
import com.hawkeye.gameModule.data.database.model.Folder
import com.hawkeye.gameModule.data.database.model.Record
import com.hawkeye.gameModule.data.database.model.SavedGame
import com.hawkeye.gameModule.data.database.model.SudokuBoard

@Database(
    entities = [Record::class, SudokuBoard::class, SavedGame::class, Folder::class],
    version = 5,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5)
    ]
)
@TypeConverters(
    DurationConverter::class,
    ZonedDateTimeConverter::class,
    GameDifficultyConverter::class,
    GameTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao
    abstract fun boardDao(): BoardDao
    abstract fun savedGameDao(): SavedGameDao

    abstract fun folderDao(): FolderDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "main_database"
                ).build()
            }

            return INSTANCE as AppDatabase
        }
    }
}