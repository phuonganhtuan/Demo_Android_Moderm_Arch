package com.example.androidbp.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbp.data.models.DemoEntity
import com.example.androidbp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: MainRepository) : ViewModel() {

    val activityIntent = Channel<MainIntent>(Channel.UNLIMITED)

    val state: StateFlow<MainState>
        get() = _state
    private val _state = MutableStateFlow<MainState>(MainState.Idle)

    val localActivityList: StateFlow<List<DemoEntity>> get() = _localActivityList
    private val _localActivityList = repo.getAllEntities()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        observeIntentEvents()
    }

    private fun observeIntentEvents() {
        viewModelScope.launch {
            activityIntent.consumeAsFlow().collect {
                when (it) {
                    MainIntent.FetchNewActivity -> {
                        getRandomActivity()
                    }
                }
            }
        }
    }

    private fun getRandomActivity() = viewModelScope.launch {

        _state.value = MainState.Loading
        try {
            withContext(Dispatchers.IO) {
                flowOf(repo.getRandomActivity()).collect {
                    addEntity(it)
                }
            }
            withContext(Dispatchers.Main) {
                _state.value = MainState.ActivitySuccess
                _state.value = MainState.Idle
            }
        } catch (exception: Exception) {
            _state.value = MainState.Error
            _state.value = MainState.Idle
        }
    }

    private suspend fun addEntity(entity: DemoEntity) {
        Log.i("aaa", "Adding entity into DB...")
        repo.addEntity(entity)
    }
}
