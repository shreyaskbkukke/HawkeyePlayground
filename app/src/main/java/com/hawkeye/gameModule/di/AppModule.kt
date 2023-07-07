package com.hawkeye.gameModule.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.hawkeye.gameModule.data.database.AppDatabase
import com.hawkeye.gameModule.data.database.dao.BoardDao
import com.hawkeye.gameModule.data.database.dao.FolderDao
import com.hawkeye.gameModule.data.database.dao.RecordDao
import com.hawkeye.gameModule.data.database.dao.SavedGameDao
import com.hawkeye.gameModule.data.database.repository.BoardRepositoryImpl
import com.hawkeye.gameModule.data.database.repository.FolderRepositoryImpl
import com.hawkeye.gameModule.data.database.repository.RecordRepositoryImpl
import com.hawkeye.gameModule.data.database.repository.SavedGameRepositoryImpl
import com.hawkeye.gameModule.data.datastore.AppSettingsManager
import com.hawkeye.gameModule.data.datastore.ThemeSettingsManager
import com.hawkeye.gameModule.domain.repository.BoardRepository
import com.hawkeye.gameModule.domain.repository.FolderRepository
import com.hawkeye.gameModule.domain.repository.RecordRepository
import com.hawkeye.gameModule.domain.repository.SavedGameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val ACRA_SHARED_PREFS_NAME = "acra_shared_pref"
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAcraSharedPrefs(@ApplicationContext context: Context): SharedPreferences
        = context.getSharedPreferences(ACRA_SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideFolderRepository(folderDao: FolderDao): FolderRepository
        = FolderRepositoryImpl(folderDao)

    @Provides
    @Singleton
    fun provideFolderDao(appDatabase: AppDatabase): FolderDao = appDatabase.folderDao()

    // records
    @Singleton
    @Provides
    fun provideRecordRepository(recordDao: RecordDao): RecordRepository =
        RecordRepositoryImpl(recordDao)

    @Singleton
    @Provides
    fun provideRecordDao(appDatabase: AppDatabase): RecordDao = appDatabase.recordDao()


    // boards
    @Singleton
    @Provides
    fun provideBoardRepository(boardDao: BoardDao): BoardRepository = BoardRepositoryImpl(boardDao)

    @Singleton
    @Provides
    fun provideBoardDao(appDatabase: AppDatabase): BoardDao = appDatabase.boardDao()


    // saved games
    @Singleton
    @Provides
    fun provideSavedGameRepository(savedGameDao: SavedGameDao): SavedGameRepository =
        SavedGameRepositoryImpl(savedGameDao)

    @Singleton
    @Provides
    fun provideSavedGameDao(appDatabase: AppDatabase): SavedGameDao = appDatabase.savedGameDao()


    // settings datastore
    @Provides
    @Singleton
    fun provideAppSettingsManager(@ApplicationContext context: Context) =
        AppSettingsManager(context)

    // appTheme datastore
    @Provides
    @Singleton
    fun provideThemeSettingsManager(@ApplicationContext context: Context) =
        ThemeSettingsManager(context)

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase = AppDatabase.getInstance(context = app)
}