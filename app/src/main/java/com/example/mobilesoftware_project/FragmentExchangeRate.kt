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
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

/*
    환율 계산기를 보여주는 Fragment
 */
// 민희 인증키: YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R
// 서영 인증키: dKurvWL03OjwAVoCM4KTQaINKfx4IjHm
//https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R&searchdate=20231201&data=AP01


class FragmentExchangeRate : Fragment() {

    lateinit var eBinding: FragmentExchangeRateBinding
    lateinit var myAPI: RetrofitInterface
    val API = "dKurvWL03OjwAVoCM4KTQaINKfx4IjHm"
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

        Log.d("exchangecheck", dateFormat.format(calendar.time))
        myAPI = RetrofitConnection.getInstance().create(RetrofitInterface::class.java)

        var flag = 0

        val myDate = dateFormat.format(calendar.time)
        myAPI
            .doGetResult(API, myDate, "AP01")
            .enqueue(object : Callback<List<ClassExchange>> {
                override fun onFailure(call: Call<List<ClassExchange>>, t: Throwable) {
                    Log.d("exchangecheck", "통신 실패: ${t.message}")
                }

                override fun onResponse(
                    call: Call<List<ClassExchange>>,
                    response: Response<List<ClassExchange>>
                ) {
                    if (response.body() != null) {
                        dateExchange.add(dateExchange(myDate, response.body()!!))
                        Log.d("exchangecheck", "통신 성공 dateExchange: ${dateExchange[0].date}, ${dateExchange[0].results}")
                        flag = 1
                    } else {
                        calendar.add(Calendar.DAY_OF_MONTH, -1)
                    }
                }
            })

        val testlist : List<ClassExchange> = listOf(ClassExchange(unit="AED", deal="351.37", name="아랍에미리트 디르함"))
        dateExchange.add(dateExchange(myDate, testlist))

        val list = dateExchange[0].results
        val coupleMap = mutableMapOf<String, Double>()
        for (i in list) {
            if (i.unit == "IDR(100)" || i.unit == "JPY(100)") {
                i.deal?.let { coupleMap.put("${i.unit}", it.toDouble()/100) }
            } else {
                i.deal?.let { coupleMap.put("${i.unit}", it.toDouble()) }
            }
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
                for ((key, value) in coupleMap) {
                    if (selected == key) {
                        change = value
                    } else change = 1.0
                }
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
                for ((key, value) in coupleMap) {
                    if (selected == key) {
                        changed = value
                    } else changed = 1.0
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        eBinding.today.text = printFormat.format(Date()).toString()

        eBinding.exchange.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        eBinding.exchangeChange.setOnClickListener {

            var result = eBinding.exchange.text.toString().toDouble()
            var answer = result / change * changed

            eBinding.beExchanged.text = answer.toString()
        }

        return eBinding.root
    }
}