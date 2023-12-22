package com.example.mobilesoftware_project

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {


    // 객체를 하나만 생성하는 싱글톤 패턴을 적용
    companion object {
        // API 서버의 주소가 BASE_URL이 됩니다.
        private const val BASE_URL
        = "https://www.koreaexim.go.kr/site/program/financial/"
        private var INSTANCE: Retrofit? = null
        private val gson = GsonBuilder().setLenient().create()

        fun getInstance(): Retrofit {
            if(INSTANCE == null) {  // null인 경우에만 생성
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)  // API 베이스 URL 설정
                    .addConverterFactory(GsonConverterFactory.create()) // 1)
                    .build()
            }
            return INSTANCE!!
        }
    }
}