package com.example.androidbp.data.datasource.local.datasource

import com.example.androidbp.data.models.DemoEntity
import kotlinx.coroutines.flow.Flow

interface MainLocalDataSource {

    fun getAllEntities(): Flow<List<DemoEntity>>

    suspend fun addEntity(entity: DemoEntity)

    suspend fun deleteEntity(entity: DemoEntity)
}
