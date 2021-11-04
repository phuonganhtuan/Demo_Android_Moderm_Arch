package com.example.androidbp

import android.os.Bundle
import com.example.androidbp.base.BaseActivity
import com.example.androidbp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun inflateViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onActivityReady(savedInstanceState: Bundle?) {

    }

    override fun onActivityReady() {

    }
}
