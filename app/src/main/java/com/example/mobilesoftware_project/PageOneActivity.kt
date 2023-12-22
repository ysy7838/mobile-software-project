package com.example.mobilesoftware_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilesoftware_project.databinding.PageOneMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Context
import android.content.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

class PageOneActivity : AppCompatActivity() {

    var fileNum = 0
    val fileNameArray = arrayListOf<String>()
    lateinit var myAPI : RetrofitInterface

    override fun onCreate(savedInstanceState: Bundle?) {                // 액티비티 시작
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_one_main)

        var calendar = Calendar.getInstance()
        calendar.time = Date()
        val dateFormat = SimpleDateFormat("yyyyMMdd")

        calendar.add(Calendar.HOUR_OF_DAY, 9)
        calendar.add(Calendar.DATE, -1)

        Log.d("exchangecheck", dateFormat.format(calendar.time))
        myAPI = RetrofitConnection.getInstance().create(RetrofitInterface::class.java)

        Runnable {
            myAPI
                .doGetResult("YvYaRkYwmbIoYa2LQOqGWO0JWJyyiN3R",
                    dateFormat.format(calendar.time), "AP01")
                .enqueue(object : Callback<List<ClassExchange>> {

                    override fun onFailure(call: Call<List<ClassExchange>>, t: Throwable) {
                        Log.d("exchangecheck", "통신 실패: ${t.message}")
                    }

                    override fun onResponse(
                        call: Call<List<ClassExchange>>,
                        response: Response<List<ClassExchange>>
                    ) {
                        Log.d("exchangecheck", "통신 성공: ${response.body()}")
                    }
                })
        }.run()

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
            val filePath = "/data/data/com.example.mobilesoftware_project/shared_prefs/travel$fileNum.xml"
            val file = File(filePath)
            if (isFileExists(file) && !fileNameArray.contains("travel${fileNum}")) {
                fileNameArray.add("travel${fileNum}")
                fileNum += 1
            } else break;
        }
        binding.TripListRecycler.layoutManager = LinearLayoutManager(this)
        binding.TripListRecycler.adapter = PageOneAdapter(fileNameArray)         // 값을 저장한 리스트를 넘겨줌

        binding.fabAddTrip.setOnClickListener{      // + 버튼 누르면 새 액티비티로 전환 -> 2번쨰 액티비티로 수정해야 함
            val intent = Intent(this, PageTwoActivity::class.java)
            intent.putExtra("filename", "travel${fileNum}")
            startActivity(intent)
        }
    }

    fun isFileExists (file : File) : Boolean {
        return file.exists() && !file.isDirectory
    }
}
