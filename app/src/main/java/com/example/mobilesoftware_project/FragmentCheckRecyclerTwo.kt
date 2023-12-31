package com.example.mobilesoftware_project

import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesoftware_project.databinding.FragmentCheckRecyclerviewItemBinding

class FragmentRecyclerTwoAdapter(val checkboxList: ArrayList<ClassCheckStatus>, pref: SharedPreferences) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val editor: SharedPreferences.Editor = pref.edit()

    override fun getItemCount(): Int {
        return checkboxList.size
    }

    inner class FragmentRecyclerTwoHolder(val binding: FragmentCheckRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        val binding = FragmentCheckRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        // 기존에 저장되어 있는 것으로 체크 박스를 표시
        return FragmentRecyclerTwoHolder(binding).also { holder ->
            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                checkboxList.getOrNull(holder.adapterPosition)?.isChecked = isChecked
                checkboxList.getOrNull(holder.adapterPosition)?.isChecked?.let {
                    editor.putBoolean(checkboxList.getOrNull(holder.adapterPosition)?.id,
                        it
                    ).apply()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FragmentRecyclerTwoHolder).binding
        binding.checklistDescription.text = checkboxList[position].id
        binding.checkbox.isChecked = checkboxList[position].isChecked
    }
}