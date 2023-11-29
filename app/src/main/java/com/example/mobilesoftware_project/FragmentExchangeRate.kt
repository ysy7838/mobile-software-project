package com.example.mobilesoftware_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilesoftware_project.databinding.FragmentExchangeRateBinding

/*
    환율 계산기를 보여주는 Fragment
 */
class FragmentExchangeRate : Fragment() {
    lateinit var eBinding: FragmentExchangeRateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eBinding = FragmentExchangeRateBinding.inflate(inflater, container, false)
        return eBinding.root
    }
}