package com.example.mobilesoftware_project

import android.os.Bundle
import android.view.MenuItem
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesoftware_project.databinding.ActivityChooseBinding
import com.google.android.material.chip.Chip
import org.json.JSONArray

class ChooseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)     // toolbar 기본 타이틀 안 보이게
        supportActionBar?.setDisplayHomeAsUpEnabled(true)       // 뒤로 가기 버튼을 만듦

        val filename = intent.getStringExtra("filename")
        val sharedPreferences = getSharedPreferences("$filename", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // 저장버튼을 누를 시
        binding.saveButton.setOnClickListener {
            val selectedBasic = listOf<String>("기본 물품", "관리 용품")
            val selectedPurpose = getSelectedChipsText(binding.chipGroupPurpose)
            val selectedSports = getSelectedChipsText(binding.chipGroupSports)
            val selectTotal = selectedBasic + selectedPurpose + selectedSports

            val jsonArr = JSONArray()
            for (i in selectTotal) {
                jsonArr.put(i)
            }
            val result = jsonArr.toString()

            editor.putString("gender", intent.getStringExtra("gender"))
            editor.putString("location", intent.getStringExtra("location"))
            editor.putBoolean("haveChild", intent.getBooleanExtra("child", false))
            editor.putString("tripStart", intent.getStringExtra("tripStart"))
            editor.putString("tripEnd", intent.getStringExtra("tripEnd"))
            editor.putString("destination", intent.getStringExtra("destination"))
            editor.putString("activity", result)
            editor.apply()

            setResult(1000, intent)
            finish()
        }
    }
    private fun getSelectedChipsText(chipGroup: GridLayout): List<String> {
        val selectedChips = mutableListOf<String>()
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            if (chip.isChecked) {
                selectedChips.add(chip.text.toString())
            }
        }
        return selectedChips
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {       // 툴바에 있는 게 선택 되었을 때
        when (item.itemId) {                                            // 뒤로가기 버튼
            android.R.id.home -> {
                setResult(2000, intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
