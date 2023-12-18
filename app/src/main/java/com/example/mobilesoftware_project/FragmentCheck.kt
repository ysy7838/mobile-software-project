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

        val activityIndex = arguments?.getStringArray("activityIndex")
        val activityValue = arguments?.getBooleanArray("activityValue")
        val sex = arguments?.getString("sex")
        val isInternational = arguments?.getBoolean("isInternational")
        val haveChild = arguments?.getBoolean("haveChild")

        Log.d("bundlecheck", "${activityIndex?.get(1)}")
        Log.d("bundlecheck", "${activityValue?.get(1)}")
        Log.d("bundlecheck", "${sex}")
        Log.d("bundlecheck", "${isInternational}")
        Log.d("bundlecheck", "${haveChild}")


        cBinding.TripListRecycler.layoutManager = LinearLayoutManager(activity)
        cBinding.TripListRecycler.adapter = FragmentCheckRecyclerAdapter(activityIndex, activityValue, sex, isInternational, haveChild)
        return cBinding.root
    }
}