package com.example.rezervacije;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UnosRezervacije extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    EditText rest,datum,vrijeme, br_osoba,na_ime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_rezervacije);
        rest = findViewById(R.id.add_restoran);
        datum = findViewById(R.id.add_datum);
        vrijeme = findViewById(R.id.add_vrijeme);
        br_osoba = findViewById(R.id.add_brOsoba);
        na_ime = findViewById(R.id.add_naIme);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR , year);
        c.set(Calendar.MONTH , month);
        c.set(Calendar.DAY_OF_MONTH , dayOfMonth);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = format.format(c.getTime());

       // String currentDateString = DateFormat.getDateInstance().format(c.getTime());
       // datum = findViewById(R.id.add_datum);
        datum.setText(strDate);

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String currentDateString = hourOfDay+":"+minute;
       // vrijeme = findViewById(R.id.add_vrijeme);
        vrijeme.setText(currentDateString);

    }

    public void kalendar(View v){
        DialogFragment datepicker = new DatePickerFragment();
        datepicker.show(getSupportFragmentManager(), "date picker");
    }

    public void sat(View v){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    public void potvrda(View v){
        if (rest.getText().length() == 0 || datum.getText().length() == 0 || vrijeme.getText().length() == 0 || br_osoba.getText().length() == 0 || na_ime.getText().length() == 0){
            Toast.makeText(UnosRezervacije.this, "Podaci se moraju unijeti!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("restoran", rest.getText().toString());
        resultIntent.putExtra("datum", datum.getText().toString());
        resultIntent.putExtra("vrijeme", vrijeme.getText().toString());
        resultIntent.putExtra("br_osoba", br_osoba.getText().toString());
        resultIntent.putExtra("na_ime", na_ime.getText().toString());

        setResult(RESULT_OK, resultIntent);
        finish();
    }
    public void odustajanje(View v){
        Intent resultIntent = new Intent();
        setResult(RESULT_CANCELED, resultIntent);
        finish();
    }


}
