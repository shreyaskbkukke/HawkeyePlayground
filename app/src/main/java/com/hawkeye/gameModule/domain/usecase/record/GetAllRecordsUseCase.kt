package com.hawkeye.gameModule.domain.usecase.record

import com.hawkeye.gameModule.core.qqwing.GameDifficulty
import com.hawkeye.gameModule.core.qqwing.GameType
import com.hawkeye.gameModule.domain.repository.RecordRepository
import javax.inject.Inject

class GetAllRecordsUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    operator fun invoke(difficulty: GameDifficulty, type: GameType) = recordRepository.getAll(difficulty, type)
}