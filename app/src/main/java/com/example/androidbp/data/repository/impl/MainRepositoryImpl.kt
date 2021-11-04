package com.example.androidbp.data.repository.impl

import com.example.androidbp.data.datasource.local.datasource.MainLocalDataSource
import com.example.androidbp.data.datasource.remote.datasource.MainRemoteDataSource
import com.example.androidbp.data.models.DemoEntity
import com.example.androidbp.data.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: MainLocalDataSource,
    private val remoteDataSource: MainRemoteDataSource
) : MainRepository {

    override fun getAllEntities() = localDataSource.getAllEntities()

    override suspend fun addEntity(entity: DemoEntity) = localDataSource.addEntity(entity)

    override suspend fun deleteEntity(entity: DemoEntity) = localDataSource.deleteEntity(entity)

    override suspend fun getRandomActivity() = remoteDataSource.getRandomActivity()
}
