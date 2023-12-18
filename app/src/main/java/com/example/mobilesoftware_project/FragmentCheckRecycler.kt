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

class FragmentCheckRecyclerAdapter(
    val context: Context,
    val activityIndex: ArrayList<String>?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 중첩 체크리스트 체크 유지를 위한 배열 변수 선언
    private val basicCheck = arrayListOf<ClassCheckStatus>()
    private val self_careCheck = arrayListOf<ClassCheckStatus>()
    private val isInternationalCheck = arrayListOf<ClassCheckStatus>()
    private val bicycleCheck = arrayListOf<ClassCheckStatus>()
    private val campingCheck = arrayListOf<ClassCheckStatus>()
    private val hikingCheck = arrayListOf<ClassCheckStatus>()
    private val photoCheck = arrayListOf<ClassCheckStatus>()
    private val runningCheck = arrayListOf<ClassCheckStatus>()
    private val swimmingCheck = arrayListOf<ClassCheckStatus>()
    private val winterSportsCheck = arrayListOf<ClassCheckStatus>()
    private val workCheck = arrayListOf<ClassCheckStatus>()
    private val haveChildCheck = arrayListOf<ClassCheckStatus>()
    private val femaleCheck = arrayListOf<ClassCheckStatus>()

    override fun getItemCount(): Int = activityIndex?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder = FragmentCheckRecyclerHolder(
        FragmentCheckRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FragmentCheckRecyclerHolder).binding
        binding.checkTitle.text = activityIndex?.get(position)

        lateinit var array: Array<String>

        // 중첩 체크리스트 내부 array 전달
        when (activityIndex?.get(position)) {
            "basic" -> {
                array = context.resources.getStringArray(R.array.basic)
                for (i in 0..array.size-1) {
                    basicCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(basicCheck)
            }

            "self_care" -> {
                array = context.resources.getStringArray(R.array.self_care)
                for (i in 0..array.size-1) {
                    self_careCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(self_careCheck)
            }

            "isInternational" -> {
                array = context.resources.getStringArray(R.array.International)
                for (i in 0..array.size-1) {
                    isInternationalCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(isInternationalCheck)
            }

            "bicycle" -> {
                array = context.resources.getStringArray(R.array.bicycle)
                for (i in 0..array.size-1) {
                    bicycleCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(bicycleCheck)
            }

            "camping" -> {
                array = context.resources.getStringArray(R.array.camping)
                for (i in 0..array.size-1) {
                    campingCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(campingCheck)
            }

            "hiking" -> {
                array = context.resources.getStringArray(R.array.hiking)
                for (i in 0..array.size-1) {
                    hikingCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(hikingCheck)
            }

            "photo" -> {
                array = context.resources.getStringArray(R.array.photo)
                for (i in 0..array.size-1) {
                    photoCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(photoCheck)
            }

            "running" -> {
                array = context.resources.getStringArray(R.array.running)
                for (i in 0..array.size-1) {
                    runningCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(runningCheck)
            }

            "swimming" -> {
                array = context.resources.getStringArray(R.array.swimming)
                for (i in 0..array.size-1) {
                    swimmingCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(swimmingCheck)
            }

            "winterSports" -> {
                array = context.resources.getStringArray(R.array.winterSports)
                for (i in 0..array.size-1) {
                    winterSportsCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(winterSportsCheck)
            }

            "work" -> {
                array = context.resources.getStringArray(R.array.work)
                for (i in 0..array.size-1) {
                    workCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(workCheck)
            }

            "haveChild" -> {
                array = context.resources.getStringArray(R.array.haveChild)
                for (i in 0..array.size-1) {
                    haveChildCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(haveChildCheck)
            }

            "female" -> {
                array = context.resources.getStringArray(R.array.female)
                for (i in 0..array.size-1) {
                    femaleCheck.add(ClassCheckStatus(array[i], false))
                }
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(femaleCheck)
            }
        }
        binding.checkItem.layoutManager = LinearLayoutManager(context)
    }
}