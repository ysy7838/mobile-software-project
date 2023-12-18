package com.example.mobilesoftware_project

import android.util.Log
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesoftware_project.databinding.FragmentCheckRecyclerviewBinding
import java.util.ArrayList


class FragmentCheckRecyclerHolder(val binding: FragmentCheckRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root)

class FragmentCheckRecyclerAdapter(val context: Context, val activityIndex: ArrayList<String>?, val sex: String?, val isInternational: Boolean?, val haveChild: Boolean?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = activityIndex?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
        RecyclerView.ViewHolder = FragmentCheckRecyclerHolder(FragmentCheckRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FragmentCheckRecyclerHolder).binding
        binding.checkTitle.text = activityIndex?.get(position)

        lateinit var array : Array<String>

        when (activityIndex?.get(position)) {
            "basic" -> { array = context.resources.getStringArray(R.array.basic)}
            "self_care" -> { array = context.resources.getStringArray(R.array.self_care)}
            "isInternational" -> { array = context.resources.getStringArray(R.array.International)}
            "bicycle" -> { array = context.resources.getStringArray(R.array.bicycle)}
            "camping" -> { array = context.resources.getStringArray(R.array.camping)}
            "hiking" -> { array = context.resources.getStringArray(R.array.hiking)}
            "photo" -> { array = context.resources.getStringArray(R.array.photo)}
            "running" -> { array = context.resources.getStringArray(R.array.running)}
            "swimming" -> { array = context.resources.getStringArray(R.array.swimming)}
            "winterSports" -> { array = context.resources.getStringArray(R.array.winterSports)}
            "work" -> { array = context.resources.getStringArray(R.array.work)}
            "haveChild" -> { array = context.resources.getStringArray(R.array.haveChild)}
            "female" -> { array = context.resources.getStringArray(R.array.female)}
        }
        binding.checkItem.adapter = FragmentRecyclerTwoAdapter(array)
        binding.checkItem.layoutManager = LinearLayoutManager(context)
    }
}