package com.example.rezervacije;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPref, sp2;
    private Gson gson;
    EditText pin,restoran,datum,vrijeme,br_osoba, na_ime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pin = findViewById(R.id.pin);
        restoran = findViewById(R.id.restoran);
        datum = findViewById(R.id.datum);
        vrijeme = findViewById(R.id.vrijeme);
        br_osoba = findViewById(R.id.br_osoba);
        na_ime = findViewById(R.id.na_ime);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        gson = new Gson();


    }
    public void dodaj(View v){
        Intent i = new Intent(this, UnosRezervacije.class);
        startActivityForResult(i,1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {

                    SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    gson = new Gson();


                    pin = findViewById(R.id.pin);
                    restoran = findViewById(R.id.restoran);
                    datum = findViewById(R.id.datum);
                    vrijeme = findViewById(R.id.vrijeme);
                    br_osoba = findViewById(R.id.br_osoba);
                    na_ime = findViewById(R.id.na_ime);
                    int br = (int)(Math.random()*9999)+1000;
                    pin.setText(String.valueOf(br));
                    Rezervacija rez = new Rezervacija();

                    String returnValue = data.getStringExtra("restoran");
                    restoran.setText(returnValue);
                    rez.setRestoran(restoran.getText().toString());

                    returnValue = data.getStringExtra("datum");
                    datum.setText(returnValue);
                    rez.setDatum(datum.getText().toString());

                    returnValue = data.getStringExtra("vrijeme");
                    vrijeme.setText(returnValue);
                    rez.setVrijeme(vrijeme.getText().toString());

                    returnValue = data.getStringExtra("br_osoba");
                    br_osoba.setText(returnValue);
                    rez.setBr_osoba(Integer.valueOf(br_osoba.getText().toString()));

                    returnValue = data.getStringExtra("na_ime");
                    na_ime.setText(returnValue);
                    rez.setNa_ime(na_ime.getText().toString());


                    String json = gson.toJson(rez);
                    editor.putString(pin.getText().toString(),json);
                    editor.commit();

                }
                break;
            }
        }
    }
    public void izbrisi(View v){
        pin = findViewById(R.id.pin);
        restoran = findViewById(R.id.restoran);
        datum = findViewById(R.id.datum);
        vrijeme = findViewById(R.id.vrijeme);
        br_osoba = findViewById(R.id.br_osoba);
        na_ime = findViewById(R.id.na_ime);

        SharedPreferences.Editor editor = sharedPref.edit();
        if (!sharedPref.contains(pin.getText().toString())){
            Toast.makeText(MainActivity.this, "Podatak s ključem "+pin.getText().toString()+" ne postoji!", Toast.LENGTH_SHORT).show();
            return;
        }
        editor.remove(pin.getText().toString());
        // Potvrdi brisanje
        editor.commit();



        String reg = pin.getText().toString();
        pin.setText("");
        restoran.setText("");
        datum.setText("");
        vrijeme.setText("");
        br_osoba.setText("");
        na_ime.setText("");



        Toast.makeText(this, "Registracija s pinom "+reg+" je obrisana!", Toast.LENGTH_SHORT).show();
    }

    public void izmjena(View v){
        pin = findViewById(R.id.pin);
        restoran = findViewById(R.id.restoran);
        datum = findViewById(R.id.datum);
        vrijeme = findViewById(R.id.vrijeme);
        br_osoba = findViewById(R.id.br_osoba);
        na_ime = findViewById(R.id.na_ime);
        String pn = pin.getText().toString();

        if (!sharedPref.contains(pin.getText().toString())){
            Toast.makeText(MainActivity.this, "Podatak s pinom "+pn+" ne postoji!", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        gson = new Gson();
        Rezervacija rez = new Rezervacija();

        rez.setRestoran(restoran.getText().toString());
        rez.setDatum(datum.getText().toString());
        rez.setVrijeme(vrijeme.getText().toString());
        rez.setBr_osoba(Integer.valueOf(br_osoba.getText().toString()));
        rez.setNa_ime(na_ime.getText().toString());


        String json = gson.toJson(rez);
        editor.putString(pin.getText().toString(),json);
        editor.commit();

        Toast.makeText(this, "Registracija s pinom "+pn+" je izmjenjena!", Toast.LENGTH_SHORT).show();
    }

    public void citanje(View v){


        pin = findViewById(R.id.pin);
        restoran = findViewById(R.id.restoran);
        datum = findViewById(R.id.datum);
        vrijeme = findViewById(R.id.vrijeme);
        br_osoba = findViewById(R.id.br_osoba);
        na_ime = findViewById(R.id.na_ime);

        Rezervacija rez = gson.fromJson(sharedPref.getString(pin.getText().toString(),""), Rezervacija.class);
        if (rez == null){
            Toast.makeText(MainActivity.this, "Rezervacija ne postoji u spremištu!", Toast.LENGTH_SHORT).show();
            return;
        }

        restoran.setText(rez.getRestoran());
        datum.setText(rez.getDatum());
        vrijeme.setText(rez.getVrijeme());
        br_osoba.setText(String.valueOf(rez.getBr_osoba()));
        na_ime.setText(rez.getNa_ime());

    }

}

