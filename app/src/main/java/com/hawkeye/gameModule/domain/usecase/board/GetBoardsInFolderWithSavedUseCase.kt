package com.hawkeye.gameModule.domain.usecase.board

import com.hawkeye.gameModule.domain.repository.BoardRepository
import javax.inject.Inject

class GetBoardsInFolderWithSavedUseCase @Inject constructor(
    private val boardRepository: BoardRepository
){
    operator fun invoke(folderUid: Long) = boardRepository.getInFolderWithSaved(folderUid)
}