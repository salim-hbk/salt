package com.salt.datedialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;


/**
 * Created by Saleem on 12/9/2016.
 */

public class DialogDatePicker implements NumberPicker.OnValueChangeListener {


    private static Dialog dialogBox = null;
    private final static int MAX_YEARS = 100;

    private NumberPicker nPYear;
    private NumberPicker nPmonth;
    private NumberPicker nPdate;

    private int YEAR_SELECTED;
    private int MONTH_SELECTED;
    private int DATE_SELECTED;
    private Calendar calendar = Calendar.getInstance();
    private String[] yearsDispValues;
    private String[] yearsDispValuesNumber;
    private String[] monthsDispValues;

    private Context context;
    private TextView tVDateLabel;
    private OnDatePickerValueSet mListenerDate;
    private String yearString;

    public DialogDatePicker(Context context, OnDatePickerValueSet inp_mListenerDate) {
        this.context = context;
        this.mListenerDate = inp_mListenerDate;
        dialogBox = new Dialog(context);
        dialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogBox.setContentView(R.layout.dialog_datepicker);
        dialogBox.setCancelable(false);
        Button okBtn =  dialogBox.findViewById(R.id.tvOkDialogBtn);
        Button cancelBtn =  dialogBox.findViewById(R.id.tvcancelDialogBtn);
        tVDateLabel =  dialogBox.findViewById(R.id.tVDateLabel);
        createDatePicker();


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenerDate.onDateSet(YEAR_SELECTED, (MONTH_SELECTED), DATE_SELECTED);
                dialogBox.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBox.dismiss();
            }
        });
        dialogBox.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void showDatePickerDialog() {

        if (dialogBox != null && !dialogBox.isShowing())
            dialogBox.show();
    }

   private String dayString;

    private void createDatePicker() {
        nPYear = dialogBox.findViewById(R.id.nPyear);
        nPmonth =  dialogBox.findViewById(R.id.nPmonth);
        nPdate =  dialogBox.findViewById(R.id.nPdate);

        dayString = " " + context.getString(R.string.day);
        yearString = " " + context.getString(R.string.year);


        setYearsToDatePicker();
        setMonthsToDatePicker();
        initDatesToDatePicker();
        onDateSetOnPickers();

        nPYear.setOnValueChangedListener(this);
        nPmonth.setOnValueChangedListener(this);
        nPdate.setOnValueChangedListener(this);

    }

    private Calendar calendarDates = Calendar.getInstance();

    private void initDatesToDatePicker() {
        int numDays = calendarDates.getActualMaximum(Calendar.DATE);
        String[] datesDispValues = new String[numDays];
        for (int i = 1; i <= numDays; i++) {
            datesDispValues[i - 1] = i + dayString;
        }

        nPdate.setDisplayedValues(null);
        nPdate.setMinValue(0);
        nPdate.setMaxValue(datesDispValues.length - 1);
        nPdate.setDisplayedValues(datesDispValues);
        DATE_SELECTED = calendarDates.get(Calendar.DAY_OF_MONTH);
        nPdate.setValue(DATE_SELECTED - 1);
    }

    private void setDatesToDatePicker() {
        calendarDates.set(Calendar.YEAR, YEAR_SELECTED);
        calendarDates.set(Calendar.MONTH, MONTH_SELECTED);
        int numDays = calendarDates.getActualMaximum(Calendar.DATE);
        String[] datesDispValues = new String[numDays];
        for (int i = 1; i <= numDays; i++) {
            datesDispValues[i - 1] = i + dayString;
        }

        nPdate.setDisplayedValues(null);
        nPdate.setMinValue(0);
        nPdate.setMaxValue(datesDispValues.length - 1);
        nPdate.setDisplayedValues(datesDispValues);
    }

    private void setMonthsToDatePicker() {
        int month = calendar.get(Calendar.MONTH);
        monthsDispValues = new String[12];
        monthsDispValues = context.getResources().getStringArray(R.array.month_array);
        nPmonth.setMinValue(0);
        nPmonth.setMaxValue(monthsDispValues.length - 1);
        nPmonth.setDisplayedValues(monthsDispValues);
        MONTH_SELECTED = month;
        nPmonth.setValue(month);
    }

    private void setYearsToDatePicker() {
        int currentYear = calendar.get(Calendar.YEAR);
        yearsDispValues = new String[MAX_YEARS];
        yearsDispValuesNumber = new String[MAX_YEARS];

        int j = 0;
        for (int i = (currentYear - MAX_YEARS + 1); i <= currentYear; i++) {
            yearsDispValues[j] = i + yearString;
            yearsDispValuesNumber[j] = i + "";
            j++;
        }
        nPYear.setMinValue(0);
        nPYear.setMaxValue(yearsDispValues.length - 1);
        nPYear.setDisplayedValues(yearsDispValues);
        YEAR_SELECTED = currentYear;
        nPYear.setValue(MAX_YEARS - 1);
    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if (picker == nPYear) {
            YEAR_SELECTED = Integer.parseInt(yearsDispValuesNumber[newVal]);
            onDateSetOnPickers();
        }
        if (picker == nPmonth) {
            MONTH_SELECTED = newVal;
            setDatesToDatePicker();
            onDateSetOnPickers();
        }
        if (picker == nPdate) {
            DATE_SELECTED = newVal + 1;
            onDateSetOnPickers();
        }
    }

    private void onDateSetOnPickers() {
        //MONTH_SELECTED starts with 0 while taking int value add plus one to use it
        String dateString = YEAR_SELECTED + yearString + " - " + monthsDispValues[MONTH_SELECTED] + " - " + DATE_SELECTED + "" + dayString;
        tVDateLabel.setText(dateString);
    }

    public interface OnDatePickerValueSet {
        void onDateSet(int year, int month, int day);
    }
}
