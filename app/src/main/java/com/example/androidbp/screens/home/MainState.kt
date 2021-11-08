package com.example.androidbp.screens.home

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    object ActivitySuccess : MainState()
    object Error : MainState()
}
