package com.example.mobilesoftware_project

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesoftware_project.databinding.FragmentCheckBinding
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONArray
import kotlin.collections.ArrayList

/*
    여행 체크리스트를 보여주는 Fragment
 */
class FragmentCheck : Fragment() {
    private lateinit var cBinding: FragmentCheckBinding
    private lateinit var array: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cBinding = FragmentCheckBinding.inflate(inflater, container, false)

        val classTotal = arrayListOf<ClassCheckTotal>()
        val filename = arguments?.getString("filename")
        val pref = requireContext().getSharedPreferences("$filename", AppCompatActivity.MODE_PRIVATE)

        val gender = pref.getString("gender", "null")
        val location = pref.getString("location", "null")
        val haveChild = pref.getBoolean("haveChild", false)
        val activityArr = pref.getString("activity", "")

        val totalArr: ArrayList<String> = ArrayList()
        val arrJson = JSONArray(activityArr)
        for (i in 0 until arrJson.length()) {
            totalArr.add(arrJson.optString(i))
        }

        if (gender == "여자") totalArr.add("여성 용품")
        if (location == "국외") totalArr.add("해외 여행")
        if (haveChild) totalArr.add("아이 용품")

        val basicCheck = arrayListOf<ClassCheckStatus>()
        val self_careCheck = arrayListOf<ClassCheckStatus>()
        val isInternationalCheck = arrayListOf<ClassCheckStatus>()

        val femaleCheck = arrayListOf<ClassCheckStatus>()
        val haveChildCheck = arrayListOf<ClassCheckStatus>()

        val jobCheck = arrayListOf<ClassCheckStatus>()
        val medicCheck = arrayListOf<ClassCheckStatus>()
        val volunteerCheck = arrayListOf<ClassCheckStatus>()
        val photoCheck = arrayListOf<ClassCheckStatus>()
        val campingCheck = arrayListOf<ClassCheckStatus>()
        val hocanceCheck = arrayListOf<ClassCheckStatus>()

        val swimCheck = arrayListOf<ClassCheckStatus>()
        val runningCheck = arrayListOf<ClassCheckStatus>()
        val bicycleCheck = arrayListOf<ClassCheckStatus>()
        val hikingCheck = arrayListOf<ClassCheckStatus>()
        val winterSportsCheck = arrayListOf<ClassCheckStatus>()


        array = requireContext().resources.getStringArray(R.array.basic)
        inputItem(array, basicCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.self_care)
        inputItem(array, self_careCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.International)
        inputItem(array, isInternationalCheck, classTotal)

        array = requireContext().resources.getStringArray(R.array.female)
        inputItem(array, femaleCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.haveChild)
        inputItem(array, haveChildCheck, classTotal)

        array = requireContext().resources.getStringArray(R.array.job)
        inputItem(array, jobCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.medic)
        inputItem(array, medicCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.volunteer)
        inputItem(array, volunteerCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.photo)
        inputItem(array, photoCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.camping)
        inputItem(array, campingCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.hocance)
        inputItem(array, hocanceCheck, classTotal)

        array = requireContext().resources.getStringArray(R.array.swim)
        inputItem(array, swimCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.running)
        inputItem(array, runningCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.bicycle)
        inputItem(array, bicycleCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.hiking)
        inputItem(array, hikingCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.winterSports)
        inputItem(array, winterSportsCheck, classTotal)

        cBinding.TripListRecycler.layoutManager = LinearLayoutManager(activity)
        cBinding.TripListRecycler.adapter =
            context?.let { FragmentCheckRecyclerAdapter(it, totalArr, classTotal, pref) }
        return cBinding.root
    }

    fun inputItem(
        array: Array<String>,
        checklist: ArrayList<ClassCheckStatus>,
        classTotal: ArrayList<ClassCheckTotal>
    ) {
        val filename = arguments?.getString("filename")
        val pref: SharedPreferences =
            this.requireActivity().getSharedPreferences("$filename", AppCompatActivity.MODE_PRIVATE)

        for (element in array) {
            val check = pref.getBoolean(element, false)
            checklist.add(ClassCheckStatus(element, check))
        }
        classTotal.add(ClassCheckTotal(checklist))
    }
}