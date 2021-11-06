package com.example.androidbp.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidbp.base.BaseFragment
import com.example.androidbp.base.LoadDataState
import com.example.androidbp.data.models.DemoEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())
        composeView.setContent { HomeScreenUIs() }
        return composeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() = with(viewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loadActivityState.collect {
                    if (it == LoadDataState.SUCCESS) {
                        showToastMessage("Get new activity successfully")
                    }

                    if (it == LoadDataState.ERROR) {
                        showToastMessage("Cannot get new activity")
                    }
                }
            }
        }
    }

    private val constrainSet = ConstraintSet {
        val list = createRefFor("list")
        val button = createRefFor("button")
        val progress = createRefFor("progress")

        constrain(list) {
            top.linkTo(parent.top, margin = 0.dp)
            bottom.linkTo(parent.bottom, margin = 0.dp)
            start.linkTo(parent.start, margin = 0.dp)
            end.linkTo(parent.end, margin = 0.dp)
        }
        constrain(button) {
            bottom.linkTo(parent.bottom, margin = 24.dp)
            end.linkTo(parent.end, margin = 24.dp)
        }
        constrain(progress) {
            top.linkTo(button.top, margin = 0.dp)
            bottom.linkTo(button.bottom, margin = 0.dp)
            start.linkTo(button.start, margin = 0.dp)
            end.linkTo(button.end, margin = 0.dp)
        }
    }

    @Composable
    private fun HomeScreenUIs() {

        val activityUiState = viewModel.loadActivityState.collectAsState()
        val activityList = viewModel.localActivityList.collectAsState()

        val isButtonEnable = activityUiState.value != LoadDataState.LOADING
        val isProgressDisplay = activityUiState.value == LoadDataState.LOADING

        ConstraintLayout(constrainSet) {
            Column {
                LazyColumn(modifier = Modifier.layoutId("list")) {
                    activityList.value.asReversed().forEach {
                        item { ActivityItem(it) }
                    }
                }
            }
            FloatingActionButton(
                modifier = Modifier.layoutId("button"),
                backgroundColor = if (isButtonEnable) {
                    MaterialTheme.colors.secondary
                } else {
                    Color.Gray
                },
                onClick = { if (isButtonEnable) viewModel.getRandomActivity() }
            ) {
                Icon(Icons.Filled.Add, "")
            }
            if (isProgressDisplay) CircularProgressIndicator(modifier = Modifier.layoutId("progress"))
        }
    }

    @Composable
    private fun ActivityItem(entity: DemoEntity) {
        Text(
            text = entity.activity ?: "No activity",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}
