package com.example.mobilesoftware_project

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesoftware_project.databinding.FragmentCheckRecyclerviewBinding
import java.util.ArrayList

class FragmentCheckRecyclerHolder(val binding: FragmentCheckRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root)

class FragmentCheckRecyclerAdapter(val activityIndex: Array<String>?, activityValue: BooleanArray?, sex: String?, isInternational: Boolean?, haveChild: Boolean?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = activityIndex?.size ?: 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
        RecyclerView.ViewHolder = FragmentCheckRecyclerHolder(FragmentCheckRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FragmentCheckRecyclerHolder).binding
        Log.d("activitycheck", "${activityIndex?.get(position)}" )
    }
}