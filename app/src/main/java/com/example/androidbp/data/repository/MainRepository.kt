package com.example.androidbp.data.repository

import com.example.androidbp.data.models.DemoEntity
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAllEntities(): Flow<List<DemoEntity>>

    suspend fun addEntity(entity: DemoEntity)

    suspend fun deleteEntity(entity: DemoEntity)

    suspend fun getRandomActivity(): DemoEntity

    suspend fun getRandomActivityDelayed(): DemoEntity
}
