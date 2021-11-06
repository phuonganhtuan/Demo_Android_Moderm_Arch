package com.example.androidbp.data.datasource.local.impl

import com.example.androidbp.data.datasource.local.dao.DemoDao
import com.example.androidbp.data.datasource.local.datasource.MainLocalDataSource
import com.example.androidbp.data.models.DemoEntity
import javax.inject.Inject

class MainLocalDataSourceImpl @Inject constructor(private val dao: DemoDao) : MainLocalDataSource {

    override fun getAllEntities() = dao.getAllEntities()

    override fun addEntity(entity: DemoEntity) = dao.addEntity(entity)

    override fun deleteEntity(entity: DemoEntity) = dao.deleteEntity(entity)
}
