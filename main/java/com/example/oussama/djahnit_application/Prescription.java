package com.example.oussama.djahnit_application;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Prescription implements Parcelable {
    private int ident;

    private Pills pills;
    private String date;
    private boolean actif;
    public Prescription() {
    }
    public Prescription(int ident) {
        this.ident=ident;
        this.pills=new Pills();
        date="01/03/2019";
    }

    public Prescription(Parcel in) {
        pills = in.readParcelable(Pills.class.getClassLoader());
        ident = in.readInt();

        date = in.readString();
        actif = in.readByte() != 0; //bool==true if byte!=0
    }

    public static final Creator<Prescription> CREATOR = new Creator<Prescription>() {
        @Override
        public Prescription createFromParcel(Parcel in) {
            return new Prescription(in);
        }

        @Override
        public Prescription[] newArray(int size) {
            return new Prescription[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(pills,flags);
        dest.writeInt(ident);
        dest.writeString(date);
        dest.writeByte((byte) (actif ? 1:0));
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }


    public Pills getPills() {
        return pills;
    }

    public void setPills(Pills pills) {
        this.pills = pills;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public boolean isActif() {
        return actif;
    }

    public boolean setActif(boolean actif) {
        this.actif = actif;
        return actif;
    }
    public String toString() {
        StringBuilder tmp = new StringBuilder();
            for (Pill m : pills.getPills()) {
                tmp.append(m.getNom());
                tmp.append("/");
            }

        return tmp.toString();
    }

}
