package com.example.mobilesoftware_project

import android.util.Log
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesoftware_project.databinding.FragmentCheckRecyclerviewBinding
import java.util.ArrayList


class FragmentCheckRecyclerHolder(val binding: FragmentCheckRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root)

class FragmentCheckRecyclerAdapter(val context: Context, val totalArr: ArrayList<String>?,
                                   val classTotal: ArrayList<ClassCheckTotal>, val pref: SharedPreferences
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    // 중첩 체크리스트 체크 유지를 위한 배열 변수 선언
    private val basicCheck = classTotal[0].statusList
    private val self_careCheck = classTotal[1].statusList
    private val isInternationalCheck = classTotal[2].statusList

    private val femaleCheck = classTotal[3].statusList
    private val haveChildCheck = classTotal[4].statusList

    private val jobCheck = classTotal[5].statusList
    private val medicCheck = classTotal[6].statusList
    private val volunteerCheck = classTotal[7].statusList
    private val photoCheck = classTotal[8].statusList
    private val campingCheck = classTotal[9].statusList
    private val hocanceCheck = classTotal[10].statusList

    private val swimCheck = classTotal[11].statusList
    private val runningCheck = classTotal[12].statusList
    private val bicycleCheck = classTotal[13].statusList
    private val hikingCheck = classTotal[14].statusList
    private val winterSportsCheck = classTotal[15].statusList
    override fun getItemCount(): Int = totalArr?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {

        val binding = FragmentCheckRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FragmentCheckRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FragmentCheckRecyclerHolder).binding
        binding.checkTitle.text = totalArr?.get(position)

        // 중첩 체크리스트 내부 array 전달
        when (totalArr?.get(position)) {
            "기본 물품" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(basicCheck, pref)
            }
            "관리 용품" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(self_careCheck, pref)
            }

            "여성 용품" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(femaleCheck, pref)
            }
            "해외 여행" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(isInternationalCheck, pref)
            }
            "아이 용품" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(haveChildCheck, pref)
            }

            "업무" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(jobCheck, pref)
            }
            "의료" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(medicCheck, pref)
            }
            "봉사 활동" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(volunteerCheck, pref)
            }
            "사진 여행" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(photoCheck, pref)
            }
            "캠핑" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(campingCheck, pref)
            }
            "호캉스" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(hocanceCheck, pref)
            }


            "해변·수영" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(swimCheck, pref)
            }
            "달리기" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(runningCheck, pref)
            }
            "자전거" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(bicycleCheck, pref)
            }
            "등산" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(hikingCheck, pref)
            }
            "겨울 스포츠" -> {
                binding.checkItem.adapter = FragmentRecyclerTwoAdapter(winterSportsCheck, pref)
            }
        }
        binding.checkItem.layoutManager = LinearLayoutManager(context)
    }
}