/*
 * Copyright 2023 Stanislav Aleshin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.aleshin.features.settings.impl.domain.interactors

import kotlinx.coroutines.flow.first
import ru.aleshin.core.domain.entities.schedules.UndefinedTask
import ru.aleshin.core.domain.repository.UndefinedTasksRepository
import ru.aleshin.core.utils.functional.DomainResult
import ru.aleshin.core.utils.functional.UnitDomainResult
import ru.aleshin.features.settings.impl.domain.common.SettingsEitherWrapper
import ru.aleshin.features.settings.impl.domain.common.SettingsFailures
import javax.inject.Inject

/**
 * @author Stanislav Aleshin on 02.11.2023.
 */
internal interface UndefinedTasksInteractor {

    suspend fun addUndefinedTasks(tasks: List<UndefinedTask>): UnitDomainResult<SettingsFailures>
    suspend fun fetchAllUndefinedTasks(): DomainResult<SettingsFailures, List<UndefinedTask>>
    suspend fun removeAllUndefinedTask(): UnitDomainResult<SettingsFailures>

    class Base @Inject constructor(
        private val undefinedTasksRepository: UndefinedTasksRepository,
        private val eitherWrapper: SettingsEitherWrapper,
    ) : UndefinedTasksInteractor {

        override suspend fun addUndefinedTasks(tasks: List<UndefinedTask>) = eitherWrapper.wrap {
            undefinedTasksRepository.addOrUpdateUndefinedTasks(tasks)
        }

        override suspend fun fetchAllUndefinedTasks() = eitherWrapper.wrap {
            undefinedTasksRepository.fetchUndefinedTasks().first()
        }

        override suspend fun removeAllUndefinedTask() = eitherWrapper.wrap {
            undefinedTasksRepository.removeAllUndefinedTasks()
        }
    }
}
