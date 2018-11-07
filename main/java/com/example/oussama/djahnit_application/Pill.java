package com.example.oussama.djahnit_application;

import android.media.Image;

public class Pill {
    private String nom;
    private int dose;
    private String format;


    public Pill(String nom, int dose, String format) {
        this.nom = nom;
        this.dose = dose;
        this.format = format;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


    public String toString() {
        return nom + '.' + dose + "#" + format + '@' ;
    }
}
