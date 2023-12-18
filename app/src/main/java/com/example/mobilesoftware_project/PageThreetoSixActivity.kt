package com.example.mobilesoftware_project

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesoftware_project.databinding.PageThreeMainBinding
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

private const val CHECK = 0         // 프래그먼트를 선택할 때 사용하기 위해 선언
private const val SCHEDULE = 1
private const val WEATHER = 2
private const val EXCHANGE = 3

var fragmentCheck: FragmentCheck? = null
var fragmentSchedule: FragmentSchedule? = null
var fragmentWeather: FragmentWeather? = null
var fragmentExchangeRate: FragmentExchangeRate? = null

class PageThreetoSixActivity : AppCompatActivity() {

    lateinit var binding: PageThreeMainBinding

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_three_main)
        binding = PageThreeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
        supportActionBar?.setDisplayHomeAsUpEnabled(true)       // 뒤로 가기 버튼을 만듦

        val activityIndex: ArrayList<String>? =
            intent.getStringArrayListExtra("activityIndex")        // 데이터 전부 받아오기
        val activityValue: ArrayList<String>? = intent.getStringArrayListExtra("activityValue")
        val sex = intent.getStringExtra("sex")
        val destination = intent.getBooleanExtra("destination", false)
        val isDomestic = intent.getBooleanExtra("isDomestic", false)
        val isInternational = intent.getStringExtra("isInternational")
        val tripStart = intent.getStringExtra("tripStart")
        val tripEnd = intent.getStringExtra("tripEnd")
        val haveChild = intent.getBooleanExtra("haveChild", false)

        /*
        var calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        val date = Date()
        Log.d("exchangecheck", "${dateFormat.format(date)}")
        val dateList : MutableList<SimpleDateFormat> = mutableListOf()

        val networkService : RetrofitInterface = RetrofitConnection.getInstance().create(RetrofitInterface::class.java)
        val getResult : Call<List<ClassExchange>> = networkService.doGetResult("YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R", "$dateFormat.format(date)", "AP01")
        getResult.enqueue(object: Callback<List<ClassExchange>> {
            override fun onResponse(call: Call<List<ClassExchange>>, response: Response<List<ClassExchange>>) {
                Log.d("exchangecheck", "Success")
                val getList = response.body()
                Log.d("exchangecheck", "${getList}")
            }
            override fun onFailure(call: Call<List<ClassExchange>>, t: Throwable) {
                call.cancel()
                Log.d("exchangecheck", "t.message = ${t.message}")
                Log.d("exchangecheck", "t.cause = ${t.cause}")
            }
        })

        val exchangeTotal = arrayListOf<dateExchange>()
        val country = arrayListOf<ClassExchange>()

        val jsonString = assets.open("ExchangeData.json").reader().readText()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val unit = jsonObject.getString("cur_unit")
            val deal = (jsonObject.getString("deal_bas_r")).toDouble()
            val name = jsonObject.getString("cur_nm")

            country.add(ClassExchange(unit, deal, name))
            Log.d("exchangecheck", "$country")
        }
        exchangeTotal.add(dateExchange("2023.12.01", country))
        Log.d("exchangecheck", "$country")

         */


        initBottomNavigation()

        var bundle = Bundle()

        bundle.putStringArrayList("activityIndex", activityIndex)
        bundle.putStringArrayList("activityValue", activityValue)
        bundle.putString("sex", sex)
        bundle.putString("isInternational", isInternational)
        bundle.putBoolean("haveChild", haveChild)

        fragmentCheck!!.arguments = bundle
        supportFragmentManager!!.beginTransaction()
            .commit()
    }

    fun initBottomNavigation() {
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
        when (item.itemId) {                         // 뒤로가기 버튼이 선택되었을 때
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
}