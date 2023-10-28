package com.skillbox.lesson_12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.skillbox.lesson_12.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd-MM-yy hh:mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonData.setOnClickListener {
            val constraints = CalendarConstraints.Builder()
                .setOpenAt(calendar.timeInMillis)
                .build()

            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText("DATE")
                .setCalendarConstraints(constraints)
                .build()
            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis
                Snackbar.make(
                    binding.buttonData,
                    dateFormat.format(calendar.time),
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.buttonData.text = calendar.timeInMillis.toString()
            }
            dateDialog.show(supportFragmentManager, "tag")
        }
        binding.buttonTime.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setHour(calendar.get(Calendar.HOUR))
                .setMinute(calendar.get(Calendar.MINUTE))
                .setTitleText("DATE")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build().apply {
                    addOnPositiveButtonClickListener {
                        calendar.set(Calendar.HOUR, this.hour)
                        calendar.set(Calendar.MINUTE, this.minute)
                        Snackbar.make(
                            binding.buttonTime,
                            dateFormat.format(calendar.time),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            picker.show(supportFragmentManager, "tag")
        }
    }

}