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
package ru.aleshin.features.settings.impl.presentation.mappers

import ru.aleshin.features.settings.api.domain.entities.Settings
import ru.aleshin.features.settings.impl.presentation.models.SettingsUi

/**
 * @author Stanislav Aleshin on 30.07.2023.
 */
internal fun Settings.mapToUi() = SettingsUi(
    themeSettings = themeSettings.mapToUi(),
    tasksSettings = tasksSettings.mapToUi(),
)

internal fun SettingsUi.mapToDomain() = Settings(
    themeSettings = themeSettings.mapToDomain(),
    tasksSettings = tasksSettings.mapToDomain(),
)
