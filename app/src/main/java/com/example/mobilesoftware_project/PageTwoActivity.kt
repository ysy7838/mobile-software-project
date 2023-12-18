package com.example.mobilesoftware_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.Toolbar
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

        val sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // DatePickers 설정
        val startDate = findViewById<EditText>(R.id.start_date)
        startDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "date_picker")
            datePicker.addOnPositiveButtonClickListener {
                val dates = it
                if (dates != null) {
                    val startDateString = convertLongToTime(dates.first)
                    startDate.setText(startDateString)
                    editor.putString("start_date", startDateString)
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

            // 정보 저장
            editor.putString("gender", gender)
            editor.putString("location", location)
            editor.putString("child", child)
            editor.apply()

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
}
