package com.example.mobilesoftware_project

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesoftware_project.databinding.PageThreeMainBinding

class PageThreeActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_three_main)
        val binding = PageThreeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /*
        실습 시간에 진행했던 코드 활용해서 넣기
         */
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {        // 메뉴를 만드는 함수
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main_top, menu)                    // menu/more_menu_top.xml의 내용을 표시
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        when(item.itemId) {                     // 메뉴아이템이 선택되었을 때
            R.id.topMenu_check_activate -> {       // check_activate 상태에 따라서 다르게 되도록 -> 추후 추가해야 함.
                Log.d("HMH", "check_activate")
            }

            R.id.topMenu_trip_edit -> {            // 활동을 편집할 수 있도록
                Log.d("HMH", "trip_edit")
            }

            R.id.topMenu_trip_delete -> {          // 활동을 삭제할 수 있도록
                Log.d("HMH", "trip_delete")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}