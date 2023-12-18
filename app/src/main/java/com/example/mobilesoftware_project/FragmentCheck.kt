package com.example.mobilesoftware_project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilesoftware_project.databinding.FragmentCheckBinding
import androidx.recyclerview.widget.LinearLayoutManager

/*
    여행 체크리스트를 보여주는 Fragment
 */
class FragmentCheck : Fragment() {
    lateinit var cBinding: FragmentCheckBinding

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

        Log.d("activitycheck", "${trueActivity}")

        cBinding.TripListRecycler.layoutManager = LinearLayoutManager(activity)
        cBinding.TripListRecycler.adapter = context?.let { FragmentCheckRecyclerAdapter(it, trueActivity, sex, isInternational, haveChild) }
        return cBinding.root
    }
}