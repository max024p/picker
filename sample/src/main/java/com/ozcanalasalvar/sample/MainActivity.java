package com.ozcanalasalvar.sample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.TimeFormat;
import com.ozcanalasalvar.library.utils.DateUtils;
import com.ozcanalasalvar.library.view.datepicker.DatePicker;
import com.ozcanalasalvar.library.view.popup.DatePickerPopup;
import com.ozcanalasalvar.library.view.popup.TimePickerPopup;
import com.ozcanalasalvar.library.view.timepicker.TimePicker;


public class MainActivity extends AppCompatActivity {

    private TimePickerPopup pickerPopup;
    private DatePickerPopup datePickerPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textDate = findViewById(R.id.text_date);
        TextView textTime = findViewById(R.id.text_time);

        datePickerPopup = new DatePickerPopup.Builder()
                .from(this)
                .offset(3)
                .darkModeEnabled(true)
                .pickerMode(DatePicker.MONTH_ON_FIRST)
                .textSize(19)
                .endDate(DateUtils.INSTANCE.getTimeMiles(2050, 10, 25))
                .currentDate(DateUtils.INSTANCE.getCurrentTime())
                .startDate(DateUtils.INSTANCE.getTimeMiles(1995, 0, 1))
                .listener(new DatePickerPopup.OnDateSelectListener() {
                    @Override
                    public void onDateSelected(DatePicker dp, long date, int day, int month, int year) {
                        Toast.makeText(getApplicationContext(), "" + day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        pickerPopup = new TimePickerPopup.Builder()
                .from(this)
                .offset(3)
                .textSize(17)
                .darkModeEnabled(true)
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTime(12, 12)
                .listener(new TimePickerPopup.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelected(@Nullable TimePicker timePicker, int hour, int minute, @Nullable String format) {
                        Toast.makeText(getApplicationContext(), "" + hour + ":" + minute, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        DatePicker datePicker = findViewById(R.id.datepicker);
        datePicker.setOffset(3);
        datePicker.setDarkModeEnabled(false);
        datePicker.setTextSize(17);
        datePicker.setMaxDate(DateUtils.INSTANCE.getTimeMiles(2050, 10, 25));
        datePicker.setDate(DateUtils.INSTANCE.getCurrentTime());
        datePicker.setMinDate(DateUtils.INSTANCE.getTimeMiles(1995, 1, 12));
        datePicker.setPickerMode(DatePicker.DAY_ON_FIRST);


        datePicker.setDataSelectListener(new DatePicker.DataSelectListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSelected(long date, int day, int month, int year) {
                textDate.setText("" + day + "/" + (month + 1) + "/" + year);
            }
        });


        TimePicker timePicker = findViewById(R.id.timepicker);
        timePicker.setOffset(2);
        timePicker.setTextSize(19);
        timePicker.setTimeFormat(TimeFormat.CLOCK_12H);
        timePicker.setHour(9);
        timePicker.setMinute(5);
        timePicker.setTimeSelectListener(new TimePicker.TimeSelectListener() {

            @Override
            public void onTimeSelected(int hour, int minute, @Nullable String timeFormat) {
                textTime.setText("" + hour + ":" + minute);
            }
        });
    }


    public void openDatePicker(View view) {
        datePickerPopup.show(getSupportFragmentManager(), "sad");
    }

    public void openTimePicker(View view) {
        pickerPopup.show(getSupportFragmentManager(), "sad");
    }
}