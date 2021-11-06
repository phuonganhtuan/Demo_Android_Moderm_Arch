package com.example.androidbp.base

interface BaseContract {

    interface BasePresenter {
        fun bindView(view: BaseView)
    }

    interface BaseView {
    }
}
