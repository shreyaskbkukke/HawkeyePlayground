package com.hawkeye.gameModule.domain.usecase.board

import com.hawkeye.gameModule.domain.repository.BoardRepository
import javax.inject.Inject

class GetBoardsInFolderUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    operator fun invoke(uid: Long) = boardRepository.getBoardsInFolder(uid)
}