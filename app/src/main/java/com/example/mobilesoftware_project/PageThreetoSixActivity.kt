package com.example.mobilesoftware_project

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesoftware_project.databinding.PageThreeMainBinding
import java.util.ArrayList
import androidx.annotation.RequiresApi
import org.json.JSONArray

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


var fragmentCheck: FragmentCheck? = null
var fragmentSchedule: FragmentSchedule? = null
var fragmentWeather: FragmentWeather? = null
var fragmentExchangeRate: FragmentExchangeRate? = null

private val path = "/data/data/com.example.mobilesoftware_project/shared_prefs/"

class PageThreetoSixActivity : AppCompatActivity() {

    private lateinit var binding: PageThreeMainBinding
    lateinit var myAPI: RetrofitInterface
    val API = "dKurvWL03OjwAVoCM4KTQaINKfx4IjHm"
    val dateExchange: ArrayList<dateExchange> = arrayListOf()

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_three_main)
        binding = PageThreeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
        supportActionBar?.setDisplayHomeAsUpEnabled(true)       // 뒤로 가기 버튼을 만듦

        fragmentCheck = null
        fragmentSchedule = null
        fragmentWeather = null
        fragmentExchangeRate = null

        val filename = intent.getStringExtra("filename")

        val exchangeTotal = arrayListOf<dateExchange>()
        val country = arrayListOf<ClassExchange>()

        /*
        val jsonString = assets.open("ExchangeData.json").reader().readText()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val unit = jsonObject.getString("cur_unit")
            val deal = (jsonObject.getString("deal_bas_r"))
                .replace(",", "")
            val name = jsonObject.getString("cur_nm")

            country.add(ClassExchange(unit, deal, name))
        }
        exchangeTotal.add(dateExchange("2023.12.01", country))
         */

        var bundle = Bundle()
        bundle.putString("filename", filename)
        //bundle.putSerializable("dateExchange", dateExchange)
        initBottomNavigation(bundle)
        fragmentCheck!!.arguments = bundle

        supportFragmentManager!!.beginTransaction()
            .commit()
    }

    fun initBottomNavigation(bundle : Bundle) {
        fragmentCheck = FragmentCheck()
        supportFragmentManager.beginTransaction()
            .add(R.id.menu_frame_layout, fragmentCheck!!)
            .commit()

        binding.menuBottomNavigation.setOnItemSelectedListener {
            val trans = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.bottomMenu_checklist -> {
                    binding.ToolbarTitle.text = getString(R.string.CheckPage)
                    if (fragmentCheck == null) {
                        fragmentCheck = FragmentCheck()
                        trans.add(R.id.menu_frame_layout, fragmentCheck!!)
                    }
                    if (fragmentCheck != null) trans.show(fragmentCheck!!)
                    if (fragmentSchedule != null) trans.hide(fragmentSchedule!!)
                    if (fragmentWeather != null) trans.hide(fragmentWeather!!)
                    if (fragmentExchangeRate != null) trans.hide(fragmentExchangeRate!!)

                    trans.commit()
                }

                R.id.bottomMenu_schedule -> {
                    binding.ToolbarTitle.text = getString(R.string.SchedulePage)
                    if (fragmentSchedule == null) {
                        fragmentSchedule = FragmentSchedule()
                        fragmentSchedule?.arguments = bundle
                        trans.add(R.id.menu_frame_layout, fragmentSchedule!!)
                    }
                    if (fragmentCheck != null) trans.hide(fragmentCheck!!)
                    if (fragmentSchedule != null) trans.show(fragmentSchedule!!)
                    if (fragmentWeather != null) trans.hide(fragmentWeather!!)
                    if (fragmentExchangeRate != null) trans.hide(fragmentExchangeRate!!)
                    trans.commit()
                }

                R.id.bottomMenu_weather -> {
                    binding.ToolbarTitle.text = getString(R.string.WeatherPage)
                    if (fragmentWeather == null) {
                        fragmentWeather = FragmentWeather()
                        fragmentWeather?.arguments = bundle
                        trans.add(R.id.menu_frame_layout, fragmentWeather!!)
                    }
                    if (fragmentCheck != null) trans.hide(fragmentCheck!!)
                    if (fragmentSchedule != null) trans.hide(fragmentSchedule!!)
                    if (fragmentWeather != null) trans.show(fragmentWeather!!)
                    if (fragmentExchangeRate != null) trans.hide(fragmentExchangeRate!!)
                    trans.commit()
                }

                R.id.bottomMenu_exchange_rate -> {
                    binding.ToolbarTitle.text = getString(R.string.ExchangePage)
                    if (fragmentExchangeRate == null) {
                        fragmentExchangeRate = FragmentExchangeRate()
                        fragmentExchangeRate?.arguments = bundle
                        trans.add(R.id.menu_frame_layout, fragmentExchangeRate!!)
                    }
                    if (fragmentCheck != null) trans.hide(fragmentCheck!!)
                    if (fragmentSchedule != null) trans.hide(fragmentSchedule!!)
                    if (fragmentWeather != null) trans.hide(fragmentWeather!!)
                    if (fragmentExchangeRate != null) trans.show(fragmentExchangeRate!!)
                    trans.commit()
                }

                else -> {
                    Log.d("error", "@string/Error")
                }
            }
            return@setOnItemSelectedListener true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {            // 메뉴를 만드는 함수
        val inflater = menuInflater
        inflater.inflate(
            R.menu.menu_main_top,
            menu
        )                    // menu/more_menu_top.xml의 내용을 표시
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {       // 툴바에 있는 게 선택 되었을 때
        val filename = intent.getStringExtra("filename")

        when (item.itemId) {
            android.R.id.home -> {                    // 뒤로가기 버튼이 선택되었을 때
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
                val file = File("$path$filename.xml")
                Log.d("filecheck", "$file")
                val result = file.delete()
                if (result) {
                    finish()
                } else {
                    Toast.makeText(this, R.string.Error, Toast.LENGTH_SHORT).show()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}