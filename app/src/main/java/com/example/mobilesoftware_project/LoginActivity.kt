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
            login (email, password)
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
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    goToMainActivity(task.result?.user)
                } else {
                    //로그인에 실패한 경우 Toast 메시지로 에러를 띄워준다
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
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

/*

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.page_login)
    val binding = PageLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게

    auth = Firebase.auth

    auth!!.createUserWithEmailAndPassword(email: String, password)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                //Firebase DB에 저장 되어 있는 계정이 아닐 경우
                //입력한 계정을 새로 등록한다
                goToMainActivity(task.result?.user)
            } else if (task.exception?.message.isNullOrEmpty()) {
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
            } else {
                //입력한 계정 정보가 이미 Firebase DB에 있는 경우
                signIn(email, password)
            }
        }


    // 회원가입 창으로
    binding.gotoSignUp.setOnClickListener {
        startActivity(Intent(this, SignupActivity::class.java))
    }

    // 로그인 버튼
    binding.loginButton.setOnClickListener {
        signIn(binding.loginID.text.toString(), binding.loginPW.text.toString())
    }
}

// 로그아웃하지 않을 시 자동 로그인 , 회원가입시 바로 로그인 됨
public override fun onStart() {
    super.onStart()
    moveMainPage(auth?.currentUser)
}

// 로그인
private fun signIn(email: String, password: String) {

    if (email.isNotEmpty() && password.isNotEmpty()) {
        auth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "로그인에 성공 하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    moveMainPage(auth?.currentUser)
                } else {
                    Toast.makeText(
                        baseContext, "로그인에 실패 하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}


// 유저정보 넘겨주고 메인 액티비티 호출
fun moveMainPage(user: FirebaseUser?) {
    if (user != null) {
        startActivity(Intent(this, PageOneActivity::class.java))
        finish()
    }
}
}

 */