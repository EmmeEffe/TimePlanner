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
package ru.aleshin.timeplanner.presentation.mappers

import ru.aleshin.core.ui.models.NotificationTimeType
import ru.aleshin.core.ui.theme.tokens.TimePlannerStrings

/**
 * @author Stanislav Aleshin on 10.11.2023.
 */
fun NotificationTimeType.mapToString(strings: TimePlannerStrings) = when (this) {
    NotificationTimeType.BEFORE_TASK -> strings.beforeTaskNotifyText
    NotificationTimeType.START_TASK -> strings.startTaskNotifyText
    NotificationTimeType.AFTER_TASK -> strings.afterTaskNotifyText
}
