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
package ru.aleshin.features.editor.impl.domain.common

import ru.aleshin.core.domain.entities.template.Template
import ru.aleshin.core.utils.extensions.changeDay
import ru.aleshin.core.utils.extensions.isCurrentDay
import ru.aleshin.core.utils.extensions.shiftDay
import ru.aleshin.features.editor.impl.domain.entites.EditModel
import java.util.Date

/**
 * @author Stanislav Aleshin on 06.05.2023.
 */
internal fun Template.convertToEditModel(date: Date) = EditModel(
    date = date,
    startTime = startTime.changeDay(date),
    endTime = if (endTime.isCurrentDay(startTime)) endTime.changeDay(date) else endTime.changeDay(date.shiftDay(1)),
    createdAt = Date(),
    mainCategory = category,
    subCategory = subCategory,
    priority = priority,
    isEnableNotification = isEnableNotification,
    isConsiderInStatistics = isConsiderInStatistics,
    repeatTimes = repeatTimes,
    templateId = templateId,
)

internal fun EditModel.convertToTemplate(id: Int = 0) = Template(
    templateId = id,
    startTime = startTime,
    endTime = endTime,
    category = mainCategory,
    subCategory = subCategory,
    priority = priority,
    isEnableNotification = isEnableNotification,
    isConsiderInStatistics = isConsiderInStatistics,
    repeatTimes = repeatTimes,
)
