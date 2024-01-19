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
package ru.aleshin.features.settings.impl.presentation.models

import kotlinx.serialization.Serializable
import ru.aleshin.core.domain.entities.categories.Categories
import ru.aleshin.core.domain.entities.schedules.Schedule
import ru.aleshin.core.domain.entities.schedules.UndefinedTask
import ru.aleshin.core.domain.entities.template.Template

/**
 * @author Stanislav Aleshin on 10.06.2023.
 */
@Serializable
internal data class BackupModel(
    val schedules: List<Schedule>,
    val templates: List<Template>,
    val categories: List<Categories>,
    val undefinedTasks: List<UndefinedTask>,
)
