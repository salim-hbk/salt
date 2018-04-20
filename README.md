# salt Localised DatePicker Dialog
android library for localised date picker dialog

![date picker](https://github.com/salim-hbk/salt/blob/master/Screenshot_2018-04-20-10-28-44.png)

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


Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	       compile 'com.github.salim-hbk:salt:v0.3'
	}
