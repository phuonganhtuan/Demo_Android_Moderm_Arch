package com.example.androidbp.data.datasource.remote.datasource

import com.example.androidbp.data.models.DemoEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback

interface MainRemoteDataSource {

    fun getRandomActivity(): Call<DemoEntity>
}
