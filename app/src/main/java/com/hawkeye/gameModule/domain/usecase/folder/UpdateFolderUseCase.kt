package com.hawkeye.gameModule.domain.usecase.folder

import com.hawkeye.gameModule.data.database.model.Folder
import com.hawkeye.gameModule.domain.repository.FolderRepository
import javax.inject.Inject

class UpdateFolderUseCase @Inject constructor(
    private val folderRepository: FolderRepository
) {
    suspend operator fun invoke(folder: Folder) = folderRepository.update(folder)
}