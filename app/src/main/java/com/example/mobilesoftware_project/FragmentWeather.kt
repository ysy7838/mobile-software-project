package com.example.mobilesoftware_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilesoftware_project.databinding.FragmentWeatherBinding

/*
    날씨를 보여주는 Fragment
 */
class FragmentWeather : Fragment() {
    lateinit var wBinding: FragmentWeatherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wBinding = FragmentWeatherBinding.inflate(inflater, container, false)
        return wBinding.root
    }
}