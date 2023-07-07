package com.hawkeye.gameModule.domain.usecase.board

import com.hawkeye.gameModule.domain.repository.BoardRepository
import javax.inject.Inject

class GetBoardsInFolderFlowUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    operator fun invoke(uid: Long) = boardRepository.getBoardsInFolderFlow(uid)
}