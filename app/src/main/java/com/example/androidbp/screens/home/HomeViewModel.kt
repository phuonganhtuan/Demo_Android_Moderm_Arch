package com.example.androidbp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbp.base.LoadDataState
import com.example.androidbp.data.models.DemoEntity
import com.example.androidbp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: MainRepository) : ViewModel() {

    val localActivityList: StateFlow<List<DemoEntity>> get() = _localActivityList
    private val _localActivityList = repo.getAllEntities()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val loadActivityState: StateFlow<LoadDataState> get() = _loadActivityState
    private val _loadActivityState = MutableStateFlow(LoadDataState.NONE)

    fun getRandomActivity() = viewModelScope.launch {
        _loadActivityState.value = LoadDataState.LOADING
        try {
            flowOf(repo.getRandomActivity()).collect {
                addEntity(it)
                _loadActivityState.value = LoadDataState.SUCCESS
                _loadActivityState.value = LoadDataState.NONE
            }
        } catch (exeption: Exception) {
            _loadActivityState.value = LoadDataState.ERROR
            _loadActivityState.value = LoadDataState.NONE
        }
    }

    private fun addEntity(entity: DemoEntity) = viewModelScope.launch {
        repo.addEntity(entity)
    }
}
