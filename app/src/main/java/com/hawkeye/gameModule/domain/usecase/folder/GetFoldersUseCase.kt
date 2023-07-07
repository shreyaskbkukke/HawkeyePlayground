package com.hawkeye.gameModule.domain.usecase.folder

import com.hawkeye.gameModule.data.database.model.Folder
import com.hawkeye.gameModule.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFoldersUseCase @Inject constructor(
    private val folderRepository: FolderRepository
) {
    operator fun invoke(): Flow<List<Folder>> = folderRepository.getAll()
}