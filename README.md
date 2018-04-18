# salt
android library for localised date picker dialog

Usage

 DialogDatePicker(this, getString(R.string.day), getString(R.string.year),
                resources.getStringArray(R.array.month_array),
                object : DialogDatePicker.OnDatePickerValueSet {
                    override fun onDateSet(year: Int, month: Int, day: Int) {
                        val thisYear = year
                        val thisMonth = month + 1
                        val today = day

                    }

                }).showDatePickerDialog()
                
