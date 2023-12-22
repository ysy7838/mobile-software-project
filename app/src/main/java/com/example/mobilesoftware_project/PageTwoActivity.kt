package com.example.mobilesoftware_project

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PageTwoActivity : AppCompatActivity() {

    val WeatherAPI: String = "82374dc381666f1f98add3b5532e5714" // openweather API KEY
    lateinit var destination: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_two)
        // Toolbar 설정
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
        supportActionBar?.setDisplayHomeAsUpEnabled(true)       // 뒤로 가기 버튼을 만듦

        val filename = intent.getStringExtra("filename")

        // DatePickers 설정
        val dateRange = findViewById<EditText>(R.id.date_range)
        var peroidString = ""
        var startDateString = ""
        var endDateString = ""
        dateRange.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "date_picker")
            datePicker.addOnPositiveButtonClickListener {
                val dates = it
                if (dates != null) {
                    startDateString = convertLongToTime(dates.first)
                    endDateString = convertLongToTime(dates.second)
                    peroidString = "$startDateString - $endDateString"
                    dateRange.setText(peroidString)
                }
            }
        }

        val destiEdit = findViewById<EditText>(R.id.destination)
        destiEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                destination = destiEdit.text.toString()
                destiEdit.setOnKeyListener { v, keyCode, event ->
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        val inm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        inm.hideSoftInputFromWindow(destiEdit.windowToken, 0)
                    }
                    false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // 저장 버튼 클릭 리스너
        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {

            // radio버튼으로 저장할 정보선택
            val genderGroup = findViewById<RadioGroup>(R.id.gender_group)
            val selectedGenderId = genderGroup.checkedRadioButtonId
            val genderButton = findViewById<RadioButton>(selectedGenderId)
            val gender =
                genderButton?.text?.toString() ?: ""                           // 여기에서 뭐가 저장되는지 알아보기

            val locationGroup = findViewById<RadioGroup>(R.id.location_group)
            val selectedLocationId = locationGroup.checkedRadioButtonId
            val locationButton = findViewById<RadioButton>(selectedLocationId)
            val location = locationButton?.text?.toString() ?: ""

            val childGroup = findViewById<RadioGroup>(R.id.child_group)
            val selectedChildId = childGroup.checkedRadioButtonId
            val childButton = findViewById<RadioButton>(selectedChildId)
            val child = childButton?.text?.toString() ?: ""
            val haveChild = (child == "있음")

            if ((gender != "") && (location != "") && (child != "") && (peroidString != "") && (destination != "")) {
                val intent = Intent(this, ChooseActivity::class.java)
                intent.putExtra("filename", filename)
                intent.putExtra("gender", gender)
                intent.putExtra("location", location)
                intent.putExtra("child", haveChild)
                intent.putExtra("tripStart", startDateString)
                intent.putExtra("tripEnd", endDateString)
                intent.putExtra("destination", destination)
                startActivityForResult(intent, 1000)
            } else {
                Toast.makeText(this, R.string.makeError, Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val filename = intent.getStringExtra("filename")

            val afterIntent = Intent(this, PageThreetoSixActivity::class.java)
            afterIntent.putExtra("filename", filename)
            startActivity(afterIntent)
            finish()
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
