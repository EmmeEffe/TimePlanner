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
package ru.aleshin.features.settings.impl.di

import android.content.Context
import ru.aleshin.core.domain.repository.CategoriesRepository
import ru.aleshin.core.domain.repository.ScheduleRepository
import ru.aleshin.core.domain.repository.SubCategoriesRepository
import ru.aleshin.core.domain.repository.TasksSettingsRepository
import ru.aleshin.core.domain.repository.TemplatesRepository
import ru.aleshin.core.domain.repository.ThemeSettingsRepository
import ru.aleshin.core.domain.repository.UndefinedTasksRepository
import ru.aleshin.core.ui.notifications.TemplatesAlarmManager
import ru.aleshin.core.ui.notifications.TimeTaskAlarmManager
import ru.aleshin.core.utils.managers.CoroutineManager
import ru.aleshin.core.utils.managers.DateManager
import ru.aleshin.core.utils.navigation.Router
import ru.aleshin.module_injector.BaseFeatureDependencies

/**
 * @author Stanislav Aleshin on 17.02.2023.
 */
interface SettingsFeatureDependencies : BaseFeatureDependencies {
    val themeSettingsRepository: ThemeSettingsRepository
    val tasksSettingsRepository: TasksSettingsRepository
    val categoriesRepository: CategoriesRepository
    val subCategoriesRepository: SubCategoriesRepository
    val templatesRepository: TemplatesRepository
    val undefinedTasksRepository: UndefinedTasksRepository
    val scheduleRepository: ScheduleRepository
    val coroutineManager: CoroutineManager
    val timeTaskAlarmManager: TimeTaskAlarmManager
    val templatesAlarmManager: TemplatesAlarmManager
    val dateManager: DateManager
    val globalRouter: Router
    val applicationContext: Context
}
