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
package ru.aleshin.core.ui.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import ru.aleshin.core.domain.entities.categories.MainCategory
import ru.aleshin.core.domain.entities.categories.SubCategory
import ru.aleshin.core.domain.entities.schedules.TaskNotificationType
import ru.aleshin.core.domain.entities.schedules.TimeTask
import ru.aleshin.core.ui.mappers.mapToIcon
import ru.aleshin.core.ui.mappers.mapToString
import ru.aleshin.core.ui.models.NotificationTimeType
import ru.aleshin.core.ui.models.toTimeType
import ru.aleshin.core.ui.theme.tokens.fetchCoreIcons
import ru.aleshin.core.ui.theme.tokens.fetchCoreLanguage
import ru.aleshin.core.ui.theme.tokens.fetchCoreStrings
import ru.aleshin.core.utils.extensions.fetchCurrentLanguage
import ru.aleshin.core.utils.functional.Constants.App
import ru.aleshin.core.utils.managers.DateManager
import java.util.Date
import javax.inject.Inject

/**
 * @author Stanislav Aleshin on 28.03.2023.
 */
interface TimeTaskAlarmManager {

    fun addOrUpdateNotifyAlarm(timeTask: TimeTask)
    fun deleteNotifyAlarm(timeTask: TimeTask)

    class Base @Inject constructor(
        private val context: Context,
        private val receiverProvider: AlarmReceiverProvider,
        private val dateManager: DateManager,
    ) : TimeTaskAlarmManager {

        private val alarmManager: AlarmManager
            get() = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        private val currentTime: Date
            get() = dateManager.fetchCurrentDate()

        override fun addOrUpdateNotifyAlarm(timeTask: TimeTask) {
            timeTask.taskNotifications.toTypes(timeTask.isEnableNotification).forEach { type ->
                val alarmIntent = createAlarmIntent(timeTask.category, timeTask.subCategory, type.toTimeType())
                val id = timeTask.key + type.idAmount
                val pendingAlarmIntent = createPendingAlarmIntent(alarmIntent, id.toInt())
                val triggerTime = type.fetchNotifyTrigger(timeTask.timeRange).time
                if (triggerTime > currentTime.time) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingAlarmIntent)
                }
            }
        }

        override fun deleteNotifyAlarm(timeTask: TimeTask) {
            TaskNotificationType.values().forEach { type ->
                val alarmIntent = createAlarmIntent(timeTask.category, timeTask.subCategory, type.toTimeType())
                val id = timeTask.key + type.idAmount
                val pendingAlarmIntent = createPendingAlarmIntent(alarmIntent, id.toInt())
                alarmManager.cancel(pendingAlarmIntent)
                pendingAlarmIntent.cancel()
            }
        }

        private fun createAlarmIntent(
            category: MainCategory,
            subCategory: SubCategory?,
            timeType: NotificationTimeType = NotificationTimeType.START_TASK,
        ): Intent {
            val language = fetchCoreLanguage(context.fetchCurrentLanguage())
            val categoryName = category.let { it.default?.mapToString(fetchCoreStrings(language)) ?: it.customName }
            val subCategoryName = subCategory?.name ?: ""
            val categoryIcon = category.default?.mapToIcon(fetchCoreIcons())
            val appIcon = fetchCoreIcons().logo

            return receiverProvider.provideReceiverIntent(
                category = categoryName ?: App.NAME,
                subCategory = subCategoryName,
                icon = categoryIcon,
                appIcon = appIcon,
                timeType = timeType,
            )
        }

        private fun createPendingAlarmIntent(
            alarmIntent: Intent,
            requestId: Int,
        ) = PendingIntent.getBroadcast(
            context,
            requestId,
            alarmIntent,
            PendingIntent.FLAG_MUTABLE,
        )
    }
}
