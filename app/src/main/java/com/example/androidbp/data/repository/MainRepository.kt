package com.example.androidbp.data.repository

import com.example.androidbp.data.models.DemoEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback

interface MainRepository {

    fun getAllEntities(): List<DemoEntity>

     fun addEntity(entity: DemoEntity)

     fun deleteEntity(entity: DemoEntity)

     fun getRandomActivity(): Call<DemoEntity>
}
