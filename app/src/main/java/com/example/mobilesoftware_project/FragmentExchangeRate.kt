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
/*
import com.example.mobilesoftware_project.databinding.PageThreeMainBinding
import com.google.android.gms.common.internal.service.Common.API
import kotlinx.coroutines.NonDisposableHandle.parent
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

 */

/*
    환율 계산기를 보여주는 Fragment
 */
// 인증키: YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R
//https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R&searchdate=20231201&data=AP01


class FragmentExchangeRate : Fragment() {

    lateinit var eBinding: FragmentExchangeRateBinding
    val KRW = 1.0
    val USD = 1.3
    val EUR = 1.42
    val CNY = 0.182
    val JPY = 0.909

    var change = 1.0
    var changed = 1.0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eBinding = FragmentExchangeRateBinding.inflate(inflater, container, false)

        val spinnerChange: Spinner = eBinding.beExchangedSelect
        context?.let {
            ArrayAdapter.createFromResource(it, R.array.spinner, android.R.layout.simple_spinner_item)
                .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerChange.adapter = adapter
            }
        }
        spinnerChange.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(parent.getItemAtPosition(position).toString()) {
                    "0" -> {change = KRW
                    Log.d("changecheck", "${change}")}
                    "1" -> {change = USD
                        Log.d("changecheck", "${change}")}
                    "2" -> {change = EUR
                        Log.d("changecheck", "${change}")}
                    "3" -> {change = CNY
                        Log.d("changecheck", "${change}")}
                    "4" -> {change = JPY
                        Log.d("changecheck", "${change}")}
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val spinnerChanged: Spinner = eBinding.exchangeSelect
        context?.let {
            ArrayAdapter.createFromResource(it, R.array.spinner, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerChanged.adapter = adapter
                }
        }
        spinnerChanged.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(parent.getItemAtPosition(position).toString()) {
                    "0" -> {changed = KRW}
                    "1" -> {changed = USD}
                    "2" -> {changed = EUR}
                    "3" -> {changed = CNY}
                    "4" -> {changed = JPY}
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        eBinding.exchange.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        eBinding.exchangeChange.setOnClickListener {
            var result = eBinding.exchange.text.toString().toDouble()
            var answer = result / change * changed
            //answer = 100000.0
            eBinding.beExchanged.text = answer.toString()
        }

        return eBinding.root
    }
}