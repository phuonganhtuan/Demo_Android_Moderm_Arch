package com.example.androidbp.data.datasource.remote.datasource

import com.example.androidbp.data.models.DemoEntity
import kotlinx.coroutines.flow.Flow

interface MainRemoteDataSource {

    suspend fun getRandomActivity(): DemoEntity
}
