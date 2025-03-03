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

package ru.aleshin.core.data.datasources.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.aleshin.core.data.models.categories.MainCategoryDetails
import ru.aleshin.core.data.models.categories.MainCategoryEntity

/**
 * @author Stanislav Aleshin on 15.04.2023.
 */
@Dao
interface MainCategoriesDao {

    @Transaction
    @Query("SELECT * FROM mainCategories")
    fun fetchAllCategories(): Flow<List<MainCategoryDetails>>

    @Transaction
    @Query("SELECT * FROM mainCategories WHERE id = :id")
    suspend fun fetchCategoriesById(id: Int): MainCategoryDetails?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(entity: MainCategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategories(entities: List<MainCategoryEntity>): List<Long>

    @Query("DELETE FROM mainCategories WHERE id = :id")
    suspend fun removeCategory(id: Int)

    @Query("DELETE FROM mainCategories WHERE id > 12")
    suspend fun removeAllCategories()

    @Update
    suspend fun updateCategory(entity: MainCategoryEntity)
}
