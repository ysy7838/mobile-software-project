package com.example.mobilesoftware_project

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mobilesoftware_project.databinding.PageThreeMainBinding

private const val CHECK = 0         // 프래그먼트를 선택할 때 사용하기 위해 선언
private const val SCHEDULE = 1
private const val WEATHER = 2
private const val EXCHANGE = 3

class PageThreetoSixActivity : AppCompatActivity() {

    lateinit var binding: PageThreeMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_three_main)
        binding = PageThreeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
        supportActionBar?.setDisplayHomeAsUpEnabled(true)       // 뒤로 가기 버튼을 만듦

        val fragments: List<Fragment>                           // 프래그먼트를 리스트로 만듦
        = listOf(FragmentCheck(), FragmentSchedule(), FragmentWeather(), FragmentExchangeRate())

        val transaction = supportFragmentManager.beginTransaction()     //프래그먼트 관리자 선언
        for (i: Int in 0..3) {                                   // 각각의 프래그먼트를 추가
            transaction.add(R.id.menu_frame_layout, fragments[i])
        }
        transaction.commit()
        // 적용
        binding.menuBottomNavigation.run {          // 아래 메뉴를 선택했을 때 실행
            setOnItemSelectedListener {
                val trans = supportFragmentManager.beginTransaction()   // commit 한 번 하면 다시 선언해줘야 함
                var choice = 0                                          // 선택에 따라 달라지는 변수 선언
                when (it.itemId) {
                    R.id.bottomMenu_checklist -> {                      // 여행 체크리스트를 선택했을 때
                        binding.ToolbarTitle.text = getString(R.string.CheckPage)      // 타이틀도 바꿈 -> 이것도 프래그먼트에 추가하고 싶으면 그렇게 수정할 것 (회의해야 함)
                        choice = CHECK                                  // 값을 CHECK로 바꿈
                        showAndHide(choice, fragments, trans)           // 선택된 프래그먼트는 show, 아닌 것은 hide 하고 commit 하는 함수
                    }
                    R.id.bottomMenu_schedule -> {                       // 스케줄을 선택했을 때
                        binding.ToolbarTitle.text = getString(R.string.SchedulePage)
                        choice = SCHEDULE
                        showAndHide(choice, fragments, trans)
                    }
                    R.id.bottomMenu_weather -> {                        // 날씨를 선택했을 때
                        binding.ToolbarTitle.text = getString(R.string.WeatherPage)
                        choice = WEATHER
                        showAndHide(choice, fragments, trans)
                    }
                    R.id.bottomMenu_exchange_rate -> {                  // 환율 계산기를 선택했을 때
                        binding.ToolbarTitle.text = getString(R.string.ExchangePage)
                        choice = EXCHANGE
                        showAndHide(choice, fragments, trans)
                    }
                    else -> {                                           // 그 외의 경우가 발생하면
                        Log.d("error", "@string/Error")
                    }
                }
                true                                // return type이 boolean이라 사용
            }
            selectedItemId = R.id.bottomMenu_checklist                  // 맨 처음에는 체크리스트를 선택하도록
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {            // 메뉴를 만드는 함수
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main_top, menu)                    // menu/more_menu_top.xml의 내용을 표시
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {       // 툴바에 있는 게 선택 되었을 때
        when(item.itemId) {                         // 뒤로가기 버튼이 선택되었을 때
            android.R.id.home -> {                  // 여기에서 지금까지 한 거 저장하고 finish 해야 함
                Log.d("incomplete", "home") // incomplete 되어있는 부분은 추가가 필요
                finish()
                return true
            }

            R.id.topMenu_check_activate -> {       // check_activate 상태에 따라서 다르게 되도록
                Log.d("incomplete", "check_activate")
            }

            R.id.topMenu_activity_edit -> {            // 활동을 편집할 수 있도록
                Log.d("incomplete", "trip_edit")
            }

            R.id.topMenu_trip_delete -> {          // 활동을 삭제할 수 있도록
                Log.d("incomplete", "trip_delete")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showAndHide(num: Int, fragments: List<Fragment>, trans: FragmentTransaction) {
        for (i: Int in 0..3) {                      // 선택된 건 show 하고 아닌 건 hide 하고 commit 하는 함수
            if (num == i) trans.show(fragments[i])
            else trans.hide(fragments[i])
        }
        trans.commit()
    }
}