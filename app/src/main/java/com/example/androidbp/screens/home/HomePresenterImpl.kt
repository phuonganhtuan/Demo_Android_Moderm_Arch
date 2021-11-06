package com.example.androidbp.screens.home

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.androidbp.base.BaseContract
import com.example.androidbp.base.BasePresenter
import com.example.androidbp.data.models.DemoEntity
import com.example.androidbp.data.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.Executors
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(private val repository: MainRepository) :
    BasePresenter(), HomeContract.HomePresenter {

    init {
        Log.i("aaa", "init presenter")
    }

    private val executor = Executors.newSingleThreadExecutor()

    private lateinit var view: HomeContract.HomeView

    override fun bindView(view: BaseContract.BaseView) {
        this.view = view as HomeContract.HomeView
    }

    override fun getNewActivity() {
        try {
            repository.getRandomActivity().enqueue(object : Callback<DemoEntity> {

                override fun onResponse(call: Call<DemoEntity>, response: Response<DemoEntity>) {
                    val newActivity = response.body() ?: return
                    executor.execute {
                        addEntity(newActivity)
                        executor.shutdown()
                    }
                }

                override fun onFailure(call: Call<DemoEntity>, t: Throwable) {
                    Handler(Looper.getMainLooper()).post {
                        view.onGetActivityFailed()
                    }
                }
            })
        } catch (exception: Exception) {
            Handler(Looper.getMainLooper()).post {
                view.onGetActivityFailed()
            }
        }
    }

    override fun getAllActivities() = repository.getAllEntities()

    private fun addEntity(entity: DemoEntity) {
        repository.addEntity(entity)
        val activityList = getAllActivities()
        Log.i("aaa", activityList.size.toString())
        Handler(Looper.getMainLooper()).post {
            view.displayActivityList(activityList)
        }
    }
}
