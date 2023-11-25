package com.example.mobilesoftware_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilesoftware_project.databinding.PageOneMainBinding

class PageOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {        // 액티비티 시작
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_one_main)
        val binding = PageOneMainBinding.inflate(layoutInflater)   // 왜 mainbinding이지?
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
                                                                // 대신 안에 textView 넣어서 처리함.

        val triplistempty = arrayListOf<Trip>()
        val triplist = arrayListOf(                             // 잘 동작하는지 확인을 위해 여기에 선언
            Trip("제주", "11.11.11 ~ 11.11.22"),     // 나중에 연동 방법에 따라서 코드를 수정해야 함
            Trip("일본", "22.22.22 ~ 22.22.22"),
            Trip("중국", "33.33.33 ~ 33.33.33"),
            Trip("미국", "44.44.44 ~ 44.44.44"),
            Trip("제주", "11.11.11 ~ 11.11.22"),
            Trip("일본", "22.22.22 ~ 22.22.22"),
            Trip("중국", "33.33.33 ~ 33.33.33"),
            Trip("미국", "44.44.44 ~ 44.44.44"),
            Trip("제주", "11.11.11 ~ 11.11.22"),
            Trip("일본", "22.22.22 ~ 22.22.22"),
            Trip("중국", "33.33.33 ~ 33.33.33"),
            Trip("미국", "44.44.44 ~ 44.44.44"),
        )

        binding.TripListRecycler.layoutManager = LinearLayoutManager(this)
        binding.TripListRecycler.adapter = PageOneAdapter(triplistempty)
    }
}