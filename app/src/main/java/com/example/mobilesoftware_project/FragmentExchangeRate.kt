package com.example.mobilesoftware_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilesoftware_project.databinding.FragmentExchangeRateBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/*
    환율 계산기를 보여주는 Fragment
 */
// 인증키: YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R

//https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R&searchdate=20231201&data=AP01

class FragmentExchangeRate : Fragment() {
    lateinit var eBinding: FragmentExchangeRateBinding
    inner class NetworkThread: Thread() {
        override fun run() {
            val key = "key"
            val site = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R&searchdate=20231201&data=AP01"
            val mobileOS = "&MobileOS=AND"

            val url = URL(site)
            val conn = url.openConnection()
            val input = conn.getInputStream()
            val isr = InputStreamReader(input)
            val br = BufferedReader(isr)

            var str: String? = null
            val buf = StringBuffer()

            do {
                str = br.readLine()
                if (str != null) buf.append(str)
            } while (str != null)

            val root = JSONObject(buf.toString())
            val response = root.getJSONObject("response").getJSONObject("body").getJSONObject("items")
            val item = response.getJSONArray("item")

            for (i in 0 until item.length()) {
                val jObject = item.getJSONObject(i)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eBinding = FragmentExchangeRateBinding.inflate(inflater, container, false)
        return eBinding.root
    }
}