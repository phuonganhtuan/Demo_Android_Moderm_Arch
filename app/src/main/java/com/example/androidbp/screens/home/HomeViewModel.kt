package com.example.androidbp.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbp.base.LoadDataState
import com.example.androidbp.data.models.DemoEntity
import com.example.androidbp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: MainRepository) : ViewModel() {

    val localActivityList: StateFlow<List<DemoEntity>> get() = _localActivityList
    private val _localActivityList = repo.getAllEntities()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val loadActivityState: StateFlow<LoadDataState> get() = _loadActivityState
    private val _loadActivityState = MutableStateFlow(LoadDataState.NONE)

    @ExperimentalCoroutinesApi
    fun getRandomActivity() = viewModelScope.launch {

        _loadActivityState.value = LoadDataState.LOADING
        try {

            withContext(Dispatchers.IO) {

                // Run 10 requests in parallel, insert data into database after each request.
                (1..10).forEach { index ->
                    if (index % 2 == 0) {
                        launch {
                            flowOf(repo.getRandomActivity()).collect { entity ->
                                addEntity(entity)
                            }
                        }
                    } else {
                        launch {
                            flowOf(repo.getRandomActivityDelayed()).collect { entity ->
                                addEntity(entity)
                            }
                        }
                    }
                }
//
//                val flows = listOf(
//                    flowOf(async {
//                        repo.getRandomActivity()
//                    }),
//                    flowOf(async {
//                        repo.getRandomActivityDelayed()
//                    }),
//                    flowOf(async {
//                        repo.getRandomActivity()
//                    }),
//                    flowOf(async {
//                        repo.getRandomActivityDelayed()
//                    }),
//                    flowOf(async {
//                        repo.getRandomActivity()
//                    }),
//                    flowOf(async {
//                        repo.getRandomActivity()
//                    }),
//                    flowOf(async {
//                        repo.getRandomActivityDelayed()
//                    }),
//                    flowOf(async {
//                        repo.getRandomActivity()
//                    }),
//                    flowOf(async {
//                        repo.getRandomActivity()
//                    }),
//                    flowOf(async {
//                        repo.getRandomActivity()
//                    }),
//                )

                // Run 10 request in parallel but wait for all request to be finished before adding data into database
//                flows.merge().collect {
//                    addEntity(it.await())
//                }

//                combine(flows) {
//                    it
//                }.collect {
//                    it.forEach { result ->
//                        addEntity(result.await())
//                    }
//                }
            }
            withContext(Dispatchers.IO) {
                _loadActivityState.value = LoadDataState.SUCCESS
                _loadActivityState.value = LoadDataState.NONE
            }
        } catch (exception: Exception) {
            _loadActivityState.value = LoadDataState.ERROR
            _loadActivityState.value = LoadDataState.NONE
        }
    }

    private suspend fun addEntity(entity: DemoEntity) =
        viewModelScope.launch {
            Log.i("aaa", "Adding entity into DB...")
            repo.addEntity(entity)
        }
}
