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
package ru.aleshin.core.data.datasources.undefinedtasks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.aleshin.core.data.models.tasks.UndefinedTaskDetails
import ru.aleshin.core.data.models.tasks.UndefinedTaskEntity

/**
 * @author Stanislav Aleshin on 02.11.2023.
 */
@Dao
interface UndefinedTasksDao {

    @Query("SELECT * FROM undefinedTasks")
    @Transaction
    fun fetchAllUndefinedTasks(): Flow<List<UndefinedTaskDetails>>

    @Insert(entity = UndefinedTaskEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrUpdateUndefinedTasks(entity: List<UndefinedTaskEntity>)

    @Query("DELETE FROM undefinedTasks WHERE key = :key")
    suspend fun removeUndefinedTask(key: Long)

    @Query("DELETE FROM undefinedTasks")
    suspend fun removeAllUndefinedTasks()
}
