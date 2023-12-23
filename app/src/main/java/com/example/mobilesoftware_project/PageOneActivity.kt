package com.example.mobilesoftware_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilesoftware_project.databinding.PageOneMainBinding
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.File

class PageOneActivity : AppCompatActivity() {

    var fileNum = 0
    val fileNameArray = arrayListOf<String>()
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {                // 액티비티 시작
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_one_main)
        auth = Firebase.auth

        /*
        val binding = PageOneMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)             // toolbar 기본 타이틀 안 보이게 -> textView로 따로 처리

        /*
        //val triplist = mutableListOf<ClassTrip>()
        //val db: FirebaseFirestore = FirebaseFirestore.getInstance()

         */

        while (true) {
            val filePath = "/data/data/com.example.mobilesoftware_project/shared_prefs/travel$fileNum.xml"
            val file = File(filePath)
            Log.d("sharedPreferenceCheck", "$filePath")
            if (isFileExists(file) && !fileNameArray.contains("travel${fileNum}")) {
                fileNameArray.add("travel${fileNum}")
                fileNum += 1
            } else break;
        }
        Log.d("sharedPreferenceCheck", "$fileNum")
        Log.d("sharedPreferenceCheck", "$fileNameArray")

        binding.TripListRecycler.layoutManager = LinearLayoutManager(this)
        binding.TripListRecycler.adapter = PageOneAdapter(fileNameArray)         // 값을 저장한 리스트를 넘겨줌

        binding.fabAddTrip.setOnClickListener{      // + 버튼 누르면 새 액티비티로 전환 -> 2번쨰 액티비티로 수정해야 함
            val intent = Intent(this, PageTwoActivity::class.java)
            intent.putExtra("filename", "travel${fileNum}")
            startActivity(intent)
        }

        /*
        //val sharedPref : SharedPreferences = getSharedPreferences("travel${fileNum}", Context.MODE_PRIVATE)
        //val editor = sharedPref.edit()

        // Firebase DB 연결

        db.collection("travels")                // 컬렉션 지정
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
         */


         */
    }

    override fun onResume() {
        super.onResume()
        val binding = PageOneMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)             // toolbar 기본 타이틀 안 보이게 -> textView로 따로 처리


        while (true) {
            val filePath =
                "/data/data/com.example.mobilesoftware_project/shared_prefs/travel$fileNum.xml"
            val file = File(filePath)
            if (isFileExists(file) && !fileNameArray.contains("travel${fileNum}")) {
                fileNameArray.add("travel${fileNum}")
                fileNum += 1
            } else break;
        }
        binding.TripListRecycler.layoutManager = LinearLayoutManager(this)
        binding.TripListRecycler.adapter = PageOneAdapter(fileNameArray)         // 값을 저장한 리스트를 넘겨줌

        binding.fabAddTrip.setOnClickListener {
            val intent = Intent(this, PageTwoActivity::class.java)
            intent.putExtra("filename", "travel${fileNum}")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {            // 메뉴를 만드는 함수
        val inflater = menuInflater
        inflater.inflate(
            R.menu.menu_logout,
            menu
        )                    // menu/more_menu_top.xml의 내용을 표시
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {       // 툴바에 있는 게 선택 되었을 때

        when (item.itemId) {
            R.id.topMenu_logout -> {
                auth?.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return true
    }

    fun isFileExists(file: File): Boolean {
        return file.exists() && !file.isDirectory
    }

}
