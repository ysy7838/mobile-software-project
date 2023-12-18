package com.example.mobilesoftware_project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilesoftware_project.databinding.FragmentCheckBinding
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.ArrayList

/*
    여행 체크리스트를 보여주는 Fragment
 */
class FragmentCheck : Fragment() {
    lateinit var cBinding: FragmentCheckBinding
    lateinit var array: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cBinding = FragmentCheckBinding.inflate(inflater, container, false)

        val activityIndex = arguments?.getStringArrayList("activityIndex")
        val activityValue = arguments?.getStringArrayList("activityValue")
        val sex = arguments?.getString("sex")
        val isInternational = arguments?.getBoolean("isInternational")
        val haveChild = arguments?.getBoolean("haveChild")

        val trueActivity = arrayListOf<String>()
        if (activityValue != null) {
            for (i in 0..activityValue.lastIndex) {
                if (activityValue[i] == "true") {
                    activityIndex?.get(i)?.let { trueActivity.add(it) }
                }
            }
        }

        if (sex == "female") trueActivity.add("female")
        if (isInternational == true) trueActivity.add("isInternational")
        if (haveChild == true) trueActivity.add("haveChild")

        val classTotal = arrayListOf<ClassCheckTotal>()

        val basicCheck = arrayListOf<ClassCheckStatus>()
        val self_careCheck = arrayListOf<ClassCheckStatus>()
        val isInternationalCheck = arrayListOf<ClassCheckStatus>()
        val bicycleCheck = arrayListOf<ClassCheckStatus>()
        val campingCheck = arrayListOf<ClassCheckStatus>()
        val hikingCheck = arrayListOf<ClassCheckStatus>()
        val photoCheck = arrayListOf<ClassCheckStatus>()
        val runningCheck = arrayListOf<ClassCheckStatus>()
        val swimmingCheck = arrayListOf<ClassCheckStatus>()
        val winterSportsCheck = arrayListOf<ClassCheckStatus>()
        val workCheck = arrayListOf<ClassCheckStatus>()
        val haveChildCheck = arrayListOf<ClassCheckStatus>()
        val femaleCheck = arrayListOf<ClassCheckStatus>()

        array = requireContext().resources.getStringArray(R.array.basic)
        inputItem(array, basicCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.self_care)
        inputItem(array, self_careCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.International)
        inputItem(array, isInternationalCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.bicycle)
        inputItem(array, bicycleCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.camping)
        inputItem(array, campingCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.hiking)
        inputItem(array, hikingCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.photo)
        inputItem(array, photoCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.running)
        inputItem(array, runningCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.swimming)
        inputItem(array, swimmingCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.winterSports)
        inputItem(array, winterSportsCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.work)
        inputItem(array, workCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.haveChild)
        inputItem(array, haveChildCheck, classTotal)
        array = requireContext().resources.getStringArray(R.array.female)
        inputItem(array, femaleCheck, classTotal)


        cBinding.TripListRecycler.layoutManager = LinearLayoutManager(activity)
        cBinding.TripListRecycler.adapter = context?.let { FragmentCheckRecyclerAdapter(it, trueActivity, classTotal) }
        return cBinding.root
    }

    fun inputItem(array: Array<String>, checklist: ArrayList<ClassCheckStatus>, classTotal: ArrayList<ClassCheckTotal>) {
        for (i in 0..array.size-1) {
            checklist.add(ClassCheckStatus(array[i], false))
        }
        classTotal.add(ClassCheckTotal(checklist))
    }

}