package com.example.rezervacije;

public class Rezervacija {
    private int br_osoba;
    private String restoran, datum,vrijeme, na_ime;

    public Rezervacija() {

    }

    public int getBr_osoba() {
        return br_osoba;
    }

    public void setBr_osoba(int br_osoba) {
        this.br_osoba = br_osoba;
    }

    public String getRestoran() {
        return restoran;
    }

    public void setRestoran(String restoran) {
        this.restoran = restoran;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }

    public String getNa_ime() {
        return na_ime;
    }

    public void setNa_ime(String na_ime) {
        this.na_ime = na_ime;
    }
}
