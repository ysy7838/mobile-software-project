package com.example.mobilesoftware_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilesoftware_project.databinding.PageOneMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class PageOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {                // 액티비티 시작
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_one_main)
        val binding = PageOneMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)                 // toolbar 기본 타이틀 안 보이게 -> textView로 따로 처리

        val triplist = mutableListOf<ClassTrip>()                        // 동작 확인을 위해 여기에 선언
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        /* Firebase DB 연결 */
        db.collection("trips")                // 컬렉션 지정
            .get()                                          // 전체 가져오기
            .addOnSuccessListener { result ->               // 성공하면
                for (document in result) {                  // 각각의 문서를
                    val selectTrip = document.toObject(ClassTrip::class.java)   // 객체에 저장
                    triplist.add(selectTrip)                                      // 객체를 리스트에 추가
                }
                binding.TripListRecycler.layoutManager = LinearLayoutManager(this)
                binding.TripListRecycler.adapter = PageOneAdapter(triplist)         // 값을 저장한 리스트를 넘겨줌
            }
            .addOnFailureListener { exception ->            // 실패하면 로그 출력
                Log.d("DBData", "Error getting documents", exception)
            }

        binding.fabAddTrip.setOnClickListener{      // + 버튼 누르면 새 액티비티로 전환 -> 2번쨰 액티비티로 수정해야 함
            val intent = Intent(this, PageThreetoSixActivity::class.java)
            startActivity(intent)
        }
    }
}
