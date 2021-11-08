package com.example.androidbp.screens.home

sealed class MainIntent {

    object Idle: MainIntent()
    object FetchNewActivity: MainIntent()
}
