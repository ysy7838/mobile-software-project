package com.example.mobilesoftware_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class PageSplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)  // main 으로
            startActivity(intent)
            finish()                                                            // 현재 액티비티 닫기
        }, 3000)
    }
}