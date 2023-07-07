package com.hawkeye.gameModule.domain.usecase.board

import com.hawkeye.gameModule.data.database.model.SudokuBoard
import com.hawkeye.gameModule.domain.repository.BoardRepository
import javax.inject.Inject

class UpdateBoardUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    suspend operator fun invoke(board: SudokuBoard) = boardRepository.update(board)
}