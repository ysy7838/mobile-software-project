package com.example.mobilesoftware_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilesoftware_project.databinding.FragmentCheckBinding

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
        return cBinding.root
    }
}