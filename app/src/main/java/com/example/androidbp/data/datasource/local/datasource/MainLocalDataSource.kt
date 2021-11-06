package com.example.androidbp.data.datasource.local.datasource

import com.example.androidbp.data.models.DemoEntity
import kotlinx.coroutines.flow.Flow

interface MainLocalDataSource {

    fun getAllEntities(): List<DemoEntity>

     fun addEntity(entity: DemoEntity)

     fun deleteEntity(entity: DemoEntity)
}
