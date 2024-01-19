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

import kotlinx.coroutines.flow.Flow
import ru.aleshin.core.data.models.categories.MainCategoryDetails
import ru.aleshin.core.data.models.categories.MainCategoryEntity
import ru.aleshin.core.domain.entities.categories.MainCategory
import javax.inject.Inject

/**
 * @author Stanislav Aleshin on 15.04.2023.
 */
interface CategoriesLocalDataSource {

    suspend fun addMainCategory(mainCategory: MainCategoryEntity): Long
    suspend fun addMainCategories(mainCategories: List<MainCategoryEntity>): List<Long>
    fun fetchMainCategories(): Flow<List<MainCategoryDetails>>
    suspend fun fetchCategoriesByType(mainCategory: MainCategory): MainCategoryDetails?
    suspend fun updateMainCategory(mainCategory: MainCategoryEntity)
    suspend fun removeMainCategory(id: Int)
    suspend fun removeAllCategories()

    class Base @Inject constructor(
        private val mainCategoriesDao: MainCategoriesDao,
    ) : CategoriesLocalDataSource {

        override suspend fun addMainCategory(mainCategory: MainCategoryEntity): Long {
            return mainCategoriesDao.addCategory(mainCategory)
        }

        override suspend fun addMainCategories(mainCategories: List<MainCategoryEntity>): List<Long> {
            return mainCategoriesDao.addCategories(mainCategories)
        }

        override fun fetchMainCategories(): Flow<List<MainCategoryDetails>> {
            return mainCategoriesDao.fetchAllCategories()
        }

        override suspend fun fetchCategoriesByType(mainCategory: MainCategory): MainCategoryDetails? {
            return mainCategoriesDao.fetchCategoriesById(mainCategory.id)
        }

        override suspend fun updateMainCategory(mainCategory: MainCategoryEntity) {
            mainCategoriesDao.updateCategory(mainCategory)
        }

        override suspend fun removeMainCategory(id: Int) {
            mainCategoriesDao.removeCategory(id)
        }

        override suspend fun removeAllCategories() {
            mainCategoriesDao.removeAllCategories()
        }
    }
}
