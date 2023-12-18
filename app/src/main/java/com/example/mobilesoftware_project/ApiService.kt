package com.example.mobilesoftware_project

import android.os.Build
import androidx.annotation.RequiresApi
/*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

 */
import java.time.LocalDate

val key = "YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R"
@RequiresApi(Build.VERSION_CODES.O)
val date = LocalDate.now()
@RequiresApi(Build.VERSION_CODES.O)
val site = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R&searchdate=${date}&data=AP01"
