package com.example.mobilesoftware_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilesoftware_project.databinding.PageOneMainBinding

class PageOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {        // 액티비티 시작
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_one_main)
        val binding = PageOneMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
                                                                // 대신 안에 textView 넣어서 처리함.

        val triplistempty = arrayListOf<Trip>()                 // 잘 동작하는지 확인을 위해 여기에 선언
        val triplist = arrayListOf(                             // 나중에 연동 방법에 따라서 코드를 수정해야 함
            Trip("제주", "11.11.11 ~ 11.11.22"),
            Trip("", "22.22.22 ~ 22.22.22"),        // 값이 비어 있는 경우도 생각
            Trip("중국", ""),                        // 만들 때 유효성 검사 필요
            Trip("", ""),
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
        binding.TripListRecycler.adapter = PageOneAdapter(triplist)

        binding.fabAddTrip.setOnClickListener{
            val intent = Intent(this, PageThreeActivity::class.java)
            startActivity(intent)
        }
    }
}