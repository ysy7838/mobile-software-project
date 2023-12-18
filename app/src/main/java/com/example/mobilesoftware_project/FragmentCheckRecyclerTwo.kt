package com.example.mobilesoftware_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesoftware_project.databinding.FragmentCheckRecyclerviewItemBinding

class FragmentRecyclerTwoHolder(val binding: FragmentCheckRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root)

class FragmentRecyclerTwoAdapter(val arrayList: Array<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = arrayList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder = FragmentRecyclerTwoHolder(
            FragmentCheckRecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FragmentRecyclerTwoHolder).binding
        binding.checklistDescription.text = arrayList[position]
    }
}