package com.example.androidbp.data.datasource.remote.impl

import com.example.androidbp.data.datasource.remote.api.ApiService
import com.example.androidbp.data.datasource.remote.datasource.MainRemoteDataSource
import javax.inject.Inject

class MainRemoteDataSourceImpl @Inject constructor(private val api: ApiService) :
    MainRemoteDataSource {

    override fun getRandomActivity() = api.getRandomActivity()
}
