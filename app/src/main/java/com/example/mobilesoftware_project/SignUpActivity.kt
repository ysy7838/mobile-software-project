package com.example.mobilesoftware_project

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesoftware_project.databinding.PageSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_signup)
        val binding = PageSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
        supportActionBar?.setDisplayHomeAsUpEnabled(true)       // 뒤로 가기 버튼을 만듦

        auth = Firebase.auth

        // 계정 생성 버튼
        binding.SignupButton.setOnClickListener {
            val email = binding.SignupEditID.text.toString()
            val password = binding.SignupEditPW.text.toString()

            if (email != "" && password != "") {
                isRegularEmail(email, password)
            } else {
                Toast.makeText(this, R.string.LoginNeedMore, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 계정 생성
    private fun createAccount(email: String, password: String) {
        auth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, R.string.SignUpSuccess, Toast.LENGTH_SHORT).show()
                    finish() // 가입창 종료
                } else {
                    Toast.makeText(this, R.string.SignUpFail, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {       // 툴바에 있는 게 선택 되었을 때

        when (item.itemId) {
            android.R.id.home -> {                    // 뒤로가기 버튼이 선택되었을 때
                Log.d("incomplete", "home") // incomplete 되어있는 부분은 추가가 필요
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isRegularEmail(email: String, password: String) {
        val pattern = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (pattern) {
            createAccount(email, password)
        } else {
            Toast.makeText(this, R.string.SignUpEmail, Toast.LENGTH_SHORT).show()
        }
    }
}