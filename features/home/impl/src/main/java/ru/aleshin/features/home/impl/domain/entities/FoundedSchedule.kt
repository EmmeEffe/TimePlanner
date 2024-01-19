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
package ru.aleshin.features.home.impl.domain.entities

import ru.aleshin.core.domain.entities.schedules.Schedule
import ru.aleshin.core.domain.entities.template.Template
import ru.aleshin.core.utils.functional.Either

/**
 * @author Stanislav Aleshin on 04.08.2023.
 */
internal typealias FoundedSchedule = Either<List<FoundedPlannedTemplates>, Schedule>

internal typealias FoundedPlannedTemplates = Template
