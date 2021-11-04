package com.example.androidbp.screens.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidbp.R
import com.example.androidbp.base.BaseFragment
import com.example.androidbp.base.LoadDataState
import com.example.androidbp.databinding.FragmentHomeBinding
import com.example.androidbp.utils.gone
import com.example.androidbp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: ActivityAdapter

    override fun inflateViewBinding(
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
        setupEvents()
        observeData()
    }

    private fun initViews() = with(viewBinding) {
        recyclerActivities.adapter = adapter
    }

    private fun initData() = with(viewModel) {

    }

    private fun setupEvents() = with(viewBinding) {
        buttonGetActivity.setOnClickListener { viewModel.getRandomActivity() }
    }

    private fun observeData() = with(viewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                localActivityList.collect {
                    adapter.submitList(it.asReversed())
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loadActivityState.collect {
                    when (it) {
                        LoadDataState.NONE -> displayInitActivityState()
                        LoadDataState.LOADING -> displayLoadingActivityState()
                        LoadDataState.ERROR -> displayErrorActivityState()
                        LoadDataState.SUCCESS -> displaySuccessActivityState()
                    }
                }
            }
        }
    }

    private fun displayInitActivityState() = with(viewBinding) {
        progress.gone()
        buttonGetActivity.isEnabled = true
    }

    private fun displayLoadingActivityState() = with(viewBinding) {
        progress.show()
        buttonGetActivity.isEnabled = false
    }

    private fun displayErrorActivityState() = with(viewBinding) {
        progress.gone()
        buttonGetActivity.isEnabled = true
        showToastMessage(getString(R.string.error_get_activity))
    }

    private fun displaySuccessActivityState() = with(viewBinding) {
        progress.gone()
        buttonGetActivity.isEnabled = true
        showToastMessage(getString(R.string.mss_get_activity_success))
    }
}
