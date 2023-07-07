package com.hawkeye.gameModule.domain.usecase.folder

import com.hawkeye.gameModule.domain.repository.FolderRepository
import javax.inject.Inject

class CountPuzzlesFolderUseCase @Inject constructor(
    private val folderRepository: FolderRepository
) {
    operator fun invoke(uid: Long) = folderRepository.countPuzzlesFolder(uid)
}