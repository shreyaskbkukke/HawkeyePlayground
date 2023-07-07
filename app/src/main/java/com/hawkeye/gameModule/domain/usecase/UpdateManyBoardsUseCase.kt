package com.hawkeye.gameModule.domain.usecase

import com.hawkeye.gameModule.data.database.model.SudokuBoard
import com.hawkeye.gameModule.domain.repository.BoardRepository
import javax.inject.Inject

class UpdateManyBoardsUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    suspend operator fun invoke(boards: List<SudokuBoard>) = boardRepository.update(boards)
}