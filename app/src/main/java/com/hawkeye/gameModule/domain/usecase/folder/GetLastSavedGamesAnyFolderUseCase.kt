package com.hawkeye.gameModule.domain.usecase.folder

import com.hawkeye.gameModule.domain.repository.FolderRepository
import javax.inject.Inject

class GetLastSavedGamesAnyFolderUseCase @Inject constructor(
    private val folderRepository: FolderRepository
) {
    operator fun invoke(gamesCount: Int) = folderRepository.getLastSavedGamesAnyFolder(gamesCount)
}