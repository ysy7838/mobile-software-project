package com.example.mobilesoftware_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilesoftware_project.databinding.FragmentScheduleBinding

/*
    선택한 장소들을 스케줄로 보여주는 Fragment
 */
class FragmentSchedule : Fragment() {
    lateinit var sBinding: FragmentScheduleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sBinding = FragmentScheduleBinding.inflate(inflater, container, false)
        return sBinding.root
    }
}