package com.example.mobilesoftware_project

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import com.example.mobilesoftware_project.databinding.FragmentExchangeRateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

/*
    환율 계산기를 보여주는 Fragment - API 일일 횟수 마감 때문에 인증키 여러 개 생성
 */
// 인증키1: YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R
// 인증키2: dKurvWL03OjwAVoCM4KTQaINKfx4IjHm
// 인증키3: 29UAAIZdTKYL5O6oDE9MCWPs7wNn9fLW
//https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=29UAAIZdTKYL5O6oDE9MCWPs7wNn9fLW&searchdate=20231222&data=AP01


class FragmentExchangeRate : Fragment() {

    lateinit var eBinding: FragmentExchangeRateBinding
    lateinit var myAPI: RetrofitInterface
    val API = "29UAAIZdTKYL5O6oDE9MCWPs7wNn9fLW"
    val dateExchange: ArrayList<dateExchange> = arrayListOf()

    var change = 1.0
    var changed = 1.0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eBinding = FragmentExchangeRateBinding.inflate(inflater, container, false)

        var calendar = Calendar.getInstance()
        calendar.time = Date()
        val dateFormat = SimpleDateFormat("yyyyMMdd")
        val printFormat = SimpleDateFormat("yyyy년 MM월 dd일")
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN

        myAPI = RetrofitConnection.getInstance().create(RetrofitInterface::class.java)
        var coupleMap = mutableMapOf<String, Double>()

        val myDate = dateFormat.format(calendar.time)
        Log.d("exchangecheck", myDate)
        myAPI
            .doGetResult(API, myDate, "AP01")
            .enqueue(object : Callback<List<ClassExchange>> {
                override fun onFailure(call: Call<List<ClassExchange>>, t: Throwable) {
                    Log.d("exchangecheck", "통신 실패: ${t.message}")
                }
                override fun onResponse(call: Call<List<ClassExchange>>, response: Response<List<ClassExchange>>) {
                    if (response.body() != null) {
                        dateExchange.add(dateExchange(myDate, response.body()!!))
                        val list = dateExchange[0].results
                        for (i in list) {
                            if (i.unit == "IDR(100)" || i.unit == "JPY(100)") {
                                i.deal?.let { coupleMap.put(i.unit, it.replace(",", "").toDouble()/100) }
                            } else {
                                i.deal?.let { coupleMap.put(i.unit, it.replace(",", "").toDouble()) }
                            }
                        }
                        Log.d("exchangecheck", "coupleMap: $coupleMap")
                    } else {
                        Log.d("exchangecheck", "통신 성공 dateExchange but null")
                    }
                }
            })

        if (coupleMap.isEmpty()) {
            val errorMap = mutableMapOf("AED" to 354.01, "AUD" to 875.68, "BHD" to 3448.9, "BND" to 976.09, "CAD" to 973.24, "CHF" to 1507.48, "CNH" to 182.1, "DKK" to 190.84,
                "EUR" to 1422.94, "GBP" to 1643.13, "HKD" to 166.53, "IDR(100)" to 0.08380000000000001, "JPY(100)" to 9.0635, "KRW" to 1.0, "KWD" to 4225.27,
                "MYR" to 279.13, "NOK" to 125.69, "NZD" to 812.95, "SAR" to 346.67, "SEK" to 127.87, "SGD" to 976.09, "THB" to 37.16, "USD" to 1300.2)
            coupleMap = errorMap
        }

        val spinnerChange: Spinner = eBinding.beExchangedSelect
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.spinner,
                android.R.layout.simple_spinner_item
            )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerChange.adapter = adapter
                }
        }
        spinnerChange.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selected = parent.getItemAtPosition(position).toString()
                change = coupleMap[selected] ?: 1.0
                Log.d("exchangecheck", "change 화폐: $change")
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val spinnerChanged: Spinner = eBinding.exchangeSelect
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.spinner,
                android.R.layout.simple_spinner_item
            )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerChanged.adapter = adapter
                }
        }
        spinnerChanged.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selected = parent.getItemAtPosition(position).toString()
                changed = coupleMap[selected] ?: 1.0
                Log.d("exchangecheck", "changed 화폐: $changed")
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        eBinding.today.text = printFormat.format(Date()).toString()

        eBinding.exchange.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        eBinding.exchangeButton.setOnClickListener {
            var result = 0.0
            var num = eBinding.exchange.text.toString()
            if (num != "") {
                result = num.toDouble()
            }
            var answer = result / change * changed
            eBinding.beExchanged.text = df.format(answer).toString()
        }

        return eBinding.root
    }
}