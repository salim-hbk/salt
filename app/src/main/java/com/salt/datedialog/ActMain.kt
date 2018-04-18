package com.salt.datedialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Salim.UM on 18-04-2018.
 */
class ActMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DialogDatePicker(this, getString(R.string.day), getString(R.string.year),
                resources.getStringArray(R.array.month_array),
                object : DialogDatePicker.OnDatePickerValueSet {
                    override fun onDateSet(year: Int, month: Int, day: Int) {
                        val thisYear = year
                        val thisMonth = month + 1
                        val today = day

                    }

                }).showDatePickerDialog()


    }
}