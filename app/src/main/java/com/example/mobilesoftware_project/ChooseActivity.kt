package com.example.mobilesoftware_project

import android.content.Intent
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesoftware_project.databinding.ActivityChooseBinding
import com.google.android.material.chip.Chip

class ChooseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // 저장버튼을 누를 시
        binding.saveButton.setOnClickListener {
            val selectedPurposes = getSelectedChipsText(binding.chipGroup)
            val selectedSports = getSelectedChipsText(binding.chipGroupSports)

            Toast.makeText(this, "Selected Purposes: ${selectedPurposes.joinToString()}, Selected Sports: ${selectedSports.joinToString()}", Toast.LENGTH_SHORT).show()

            // PageThreetoSixActivity로 이동
            val intent = Intent(this, PageThreetoSixActivity::class.java)
            startActivity(intent)
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

    private fun setupChipGroup() {
        binding.chip1.setOnCloseIconClickListener {
            binding.chipGroup.removeView(it)
            Toast
                .makeText(this, "Removed 1st Chip", Toast.LENGTH_SHORT)
                .show()
        }
        binding.chip2.setOnCloseIconClickListener {
            binding.chipGroup.removeView(it)
            Toast
                .makeText(this, "Removed 2nd Chip", Toast.LENGTH_SHORT)
                .show()
        }
        binding.chip3.setOnCloseIconClickListener {
            binding.chipGroup.removeView(it)
            Toast
                .makeText(this, "Removed 3rd Chip", Toast.LENGTH_SHORT)
                .show()
        }
        binding.chip4.setOnCloseIconClickListener {
            binding.chipGroup.removeView(it)
            Toast
                .makeText(this, "Removed 4th Chip", Toast.LENGTH_SHORT)
                .show()
        }
        binding.chip5.setOnCloseIconClickListener {
            binding.chipGroup.removeView(it)
            Toast
                .makeText(this, "Removed 5th Chip", Toast.LENGTH_SHORT)
                .show()
        }
        binding.chip6.setOnCloseIconClickListener {
            binding.chipGroup.removeView(it)
            Toast
                .makeText(this, "Removed 6th Chip", Toast.LENGTH_SHORT)
                .show()
        }
        binding.chip7.setOnCloseIconClickListener {
            binding.chipGroup.removeView(it)
            Toast
                .makeText(this, "Removed 7th Chip", Toast.LENGTH_SHORT)
                .show()
        }
        binding.chip8.setOnCloseIconClickListener {
            binding.chipGroup.removeView(it)
            Toast
                .makeText(this, "Removed 4th Chip", Toast.LENGTH_SHORT)
                .show()
        }
        binding.chip9.setOnCloseIconClickListener {
            binding.chipGroup.removeView(it)
            Toast
                .makeText(this, "Removed 4th Chip", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
