package com.example.androidbp.screens.home

import com.example.androidbp.base.BaseContract
import com.example.androidbp.data.models.DemoEntity

interface HomeContract {

    interface HomePresenter : BaseContract.BasePresenter {
        fun getNewActivity()
        fun getAllActivities(): List<DemoEntity>
    }

    interface HomeView : BaseContract.BaseView {
        fun displayActivityList(list: List<DemoEntity>)
        fun onGetActivityFailed()
    }
}
