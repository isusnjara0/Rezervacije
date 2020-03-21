package com.example.rezervacije;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

public class UnosRezervacije extends AppCompatActivity {
    EditText rest,datum,br_osoba,na_ime;
    TimePicker tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_rezervacije);
        tp = findViewById(R.id.add_vrijeme);
        tp.setIs24HourView(true);
    }
    public void potvrda(View v){
        rest = findViewById(R.id.add_restoran);
        datum = findViewById(R.id.add_datum);
        tp = findViewById(R.id.add_vrijeme);
        br_osoba = findViewById(R.id.add_brOsoba);
        na_ime = findViewById(R.id.add_naIme);
        String vrijeme;
        vrijeme = tp.getHour()+":"+tp.getMinute();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("restoran", rest.getText().toString());
        resultIntent.putExtra("datum", datum.getText().toString());
        resultIntent.putExtra("vrijeme", vrijeme);
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
