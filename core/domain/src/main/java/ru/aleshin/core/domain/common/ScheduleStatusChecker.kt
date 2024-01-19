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
package ru.aleshin.core.domain.common

import ru.aleshin.core.domain.entities.schedules.DailyScheduleStatus
import java.util.Date
import javax.inject.Inject

/**
 * @author Stanislav Aleshin on 24.03.2023.
 */
interface ScheduleStatusChecker {

    fun fetchState(requiredDate: Date, currentDate: Date): DailyScheduleStatus

    class Base @Inject constructor() : ScheduleStatusChecker {

        override fun fetchState(requiredDate: Date, currentDate: Date): DailyScheduleStatus {
            return if (requiredDate.time > currentDate.time) {
                DailyScheduleStatus.PLANNED
            } else if (requiredDate.time < currentDate.time) {
                DailyScheduleStatus.REALIZED
            } else {
                DailyScheduleStatus.ACCOMPLISHMENT
            }
        }
    }
}
