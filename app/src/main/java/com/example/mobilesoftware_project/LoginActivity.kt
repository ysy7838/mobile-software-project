package com.example.mobilesoftware_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mobilesoftware_project.databinding.PageLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : Activity() {

    lateinit var auth: FirebaseAuth

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = PageLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            val email = binding.loginID.text.toString()
            val password = binding.loginPW.text.toString()

            if (email != "" && password != "") {
                login(email, password)
            } else {
                Toast.makeText(this, R.string.LoginNeedMore, Toast.LENGTH_SHORT).show()
            }
        }

        binding.gotoSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth?.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun login(email: String, password: String) {
        auth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //로그인에 성공한 경우 메인 화면으로 이동
                    Toast.makeText(this, R.string.LoginSuccess, Toast.LENGTH_SHORT).show()
                    goToMainActivity(task.result?.user)
                } else {
                    //로그인에 실패한 경우 Toast 메시지로 에러를 띄워준다
                    Toast.makeText(this, R.string.LoginFail, Toast.LENGTH_LONG).show()
                    Log.d("loginResult", "Error: ${task.exception}")
                }
            }
    }

    private fun goToMainActivity(user: FirebaseUser?) {
        //Firebase에 등록된 계정일 경우에만 메인 화면으로 이동
        if (user != null) {
            startActivity(Intent(this, PageOneActivity::class.java))
            finish()
        }
    }

    private fun reload() {
        startActivity(Intent(this, PageOneActivity::class.java))
        finish()
    }
}