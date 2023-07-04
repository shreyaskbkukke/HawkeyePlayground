package com.hawkeye.authModule.di

import com.hawkeye.authModule.data.AuthRepositoryImpl
import com.hawkeye.authModule.domain.repository.AuthRepository
import com.hawkeye.authModule.domain.use_case.ValidateLoginInputUseCase
import com.hawkeye.authModule.domain.use_case.ValidateRegisterInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase():ValidateLoginInputUseCase{
        return ValidateLoginInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase():ValidateRegisterInputUseCase{
        return ValidateRegisterInputUseCase()
    }

    @Provides
    @Singleton
    fun provideAuthRepository():AuthRepository{
        return com.hawkeye.authModule.data.AuthRepositoryImpl()
    }

}