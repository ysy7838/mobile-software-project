package com.example.mobilesoftware_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PageTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_two)

        // Toolbar 설정
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
        supportActionBar?.setDisplayHomeAsUpEnabled(true)       // 뒤로 가기 버튼을 만듦

        val sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE)
        //val editor2 = sharedPreferences.edit()
        val editor = mutableMapOf<String, Any>()

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val colRef : CollectionReference = db.collection("travels")
        //val docRef: Task<DocumentReference> = colRef.add()


        // DatePickers 설정
        val dateRange = findViewById<EditText>(R.id.date_range)
        dateRange.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "date_picker")
            datePicker.addOnPositiveButtonClickListener {
                val dates = it
                if (dates != null) {
                    val startDateString = convertLongToTime(dates.first)
                    val endDateString = convertLongToTime(dates.second)
                    dateRange.setText("$startDateString - $endDateString")
                    editor.put("start_date", startDateString)
                    editor.put("end_date", endDateString)
                }
            }
        }

        // 저장 버튼 클릭 리스너
        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            // radio버튼으로 저장할 정보선택
            val genderGroup = findViewById<RadioGroup>(R.id.gender_group)
            val selectedGenderId = genderGroup.checkedRadioButtonId
            val genderButton = findViewById<RadioButton>(selectedGenderId)
            val gender = genderButton.text.toString()

            val locationGroup = findViewById<RadioGroup>(R.id.location_group)
            val selectedLocationId = locationGroup.checkedRadioButtonId
            val locationButton = findViewById<RadioButton>(selectedLocationId)
            val location = locationButton.text.toString()

            val childGroup = findViewById<RadioGroup>(R.id.child_group)
            val selectedChildId = childGroup.checkedRadioButtonId
            val childButton = findViewById<RadioButton>(selectedChildId)
            val child = childButton.text.toString()
            var haveChild = true
            if (child == "있음") haveChild = true
            else haveChild = false

            // 정보 저장
            editor.put("gender", gender)
            editor.put("location", location)
            editor.put("child", haveChild)


            val passArray = arrayOf(editor)
            // ChooseActivity로 이동
            val intent = Intent(this, ChooseActivity::class.java)

            startActivity(intent)
        }
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        return format.format(date)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {       // 툴바에 있는 게 선택 되었을 때
        when (item.itemId) {                         // 뒤로가기 버튼이 선택되었을 때
            android.R.id.home -> {                  // 여기에서 지금까지 한 거 저장하고 finish 해야 함
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
