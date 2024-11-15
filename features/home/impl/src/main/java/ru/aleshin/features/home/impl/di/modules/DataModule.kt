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
package ru.aleshin.features.home.impl.di.modules

import dagger.Binds
import dagger.Module
import ru.aleshin.core.utils.di.FeatureScope
import ru.aleshin.features.home.impl.data.datasource.FeatureCategoryLocalDataSource
import ru.aleshin.features.home.impl.data.datasource.FeatureScheduleLocalDataSource
import ru.aleshin.features.home.impl.data.repositories.FeatureCategoryRepositoryImpl
import ru.aleshin.features.home.impl.data.repositories.FeatureScheduleRepositoryImpl
import ru.aleshin.features.home.impl.domain.repositories.FeatureCategoryRepository
import ru.aleshin.features.home.impl.domain.repositories.FeatureScheduleRepository

/**
 * @author Stanislav Aleshin on 05.11.2023.
 */
@Module
internal interface DataModule {

    @Binds
    @FeatureScope
    fun bindFeatureScheduleLocalDataSource(dataSource: FeatureScheduleLocalDataSource.Base): FeatureScheduleLocalDataSource

    @Binds
    fun bindFeatureScheduleRepository(repository: FeatureScheduleRepositoryImpl): FeatureScheduleRepository


    @Binds
    @FeatureScope
    fun bindFeatureCategoryLocalDataSource(dataSource: FeatureCategoryLocalDataSource.Base): FeatureCategoryLocalDataSource

    @Binds
    fun bindFeatureCategoryRepository(repository: FeatureCategoryRepositoryImpl): FeatureCategoryRepository
}
