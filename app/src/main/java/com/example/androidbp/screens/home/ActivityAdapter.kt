package com.example.androidbp.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.androidbp.base.BaseDiffCallBack
import com.example.androidbp.base.BaseViewHolder
import com.example.androidbp.data.models.DemoEntity
import com.example.androidbp.databinding.ItemRandomActivityBinding
import javax.inject.Inject

class ActivityAdapter @Inject constructor() :
    ListAdapter<DemoEntity, ActivityViewHolder>(ActivityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val itemViewBinding =
            ItemRandomActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActivityViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.displayData(getItem(position))
    }
}

class ActivityViewHolder(private val itemViewBinding: ItemRandomActivityBinding) :
    BaseViewHolder<DemoEntity>(itemViewBinding) {

    override fun displayData(entity: DemoEntity) = with(itemViewBinding) {
        textName.text = entity.activity ?: "No activity available now"
    }
}

class ActivityDiffCallback : BaseDiffCallBack<DemoEntity>()
