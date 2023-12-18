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

class FragmentCheckRecyclerAdapter(val context: Context, val activityIndex: ArrayList<String>?, val classTotal: ArrayList<ClassCheckTotal>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    // 중첩 체크리스트 체크 유지를 위한 배열 변수 선언
    private val basicCheck = classTotal[0].statusList
    private val self_careCheck = classTotal[1].statusList
    private val isInternationalCheck = classTotal[2].statusList
    private val bicycleCheck = classTotal[3].statusList
    private val campingCheck = classTotal[4].statusList
    private val hikingCheck = classTotal[5].statusList
    private val photoCheck = classTotal[6].statusList
    private val runningCheck = classTotal[7].statusList
    private val swimmingCheck = classTotal[8].statusList
    private val winterSportsCheck = classTotal[9].statusList
    private val workCheck = classTotal[10].statusList
    private val haveChildCheck = classTotal[11].statusList
    private val femaleCheck = classTotal[12].statusList

    lateinit var array: Array<String>

    override fun getItemCount(): Int = activityIndex?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {

        val binding = FragmentCheckRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FragmentCheckRecyclerHolder(binding)
    }

    fun inputItem(array: Array<String>, checklist: ArrayList<ClassCheckStatus>) {
        for (i in 0..array.size - 1) {
            checklist.add(ClassCheckStatus(array[i], false))
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FragmentCheckRecyclerHolder).binding
        binding.checkTitle.text = activityIndex?.get(position)

        // 중첩 체크리스트 내부 array 전달
        when (activityIndex?.get(position)) {
            "basic" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(basicCheck)
            }

            "self_care" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(self_careCheck)
            }

            "isInternational" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(isInternationalCheck)
            }

            "bicycle" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(bicycleCheck)
            }

            "camping" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(campingCheck)
            }

            "hiking" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(hikingCheck)
            }

            "photo" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(photoCheck)
            }

            "running" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(runningCheck)
            }

            "swimming" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(swimmingCheck)
            }

            "winterSports" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(winterSportsCheck)
            }

            "work" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(workCheck)
            }

            "haveChild" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(haveChildCheck)
            }

            "female" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(femaleCheck)
            }
        }
        binding.checkItem.layoutManager = LinearLayoutManager(context)
    }
}