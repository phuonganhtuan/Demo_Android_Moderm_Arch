package com.example.androidbp.screens.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import com.example.androidbp.R
import com.example.androidbp.base.BaseFragment
import com.example.androidbp.data.models.DemoEntity
import com.example.androidbp.databinding.FragmentHomeBinding
import com.example.androidbp.utils.gone
import com.example.androidbp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeContract.HomeView {

    @Inject
    lateinit var presenter: HomeContract.HomePresenter

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
    }

    override fun displayActivityList(list: List<DemoEntity>) = with(viewBinding) {
        progress.gone()
        buttonGetActivity.isEnabled = true
        adapter.submitList(list.asReversed())
    }

    override fun onGetActivityFailed() = with(viewBinding) {
        progress.gone()
        buttonGetActivity.isEnabled = true
        showToastMessage(getString(R.string.error_get_activity))
    }

    private fun initViews() = with(viewBinding) {
        recyclerActivities.adapter = adapter
        progress.gone()
        buttonGetActivity.isEnabled = true
    }

    private fun initData() {
        presenter.bindView(this@HomeFragment)
        getAllActivities()
    }

    private fun getAllActivities() {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val activityList = presenter.getAllActivities()
            Handler(Looper.getMainLooper()).post {
                adapter.submitList(activityList.asReversed())
            }
            executor.shutdown()
        }
    }

    private fun setupEvents() = with(viewBinding) {
        buttonGetActivity.setOnClickListener {
            progress.show()
            buttonGetActivity.isEnabled = false
            presenter.getNewActivity()
        }
    }
}
