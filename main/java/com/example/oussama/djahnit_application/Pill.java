package com.example.oussama.djahnit_application;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Pill implements Parcelable {
    private String nom;
    private int dose;
    private String format;
    private int ident=0;
    private String frequence;
    private String quand;
    private Bitmap image;

    public Pill() {
        this.nom="pardefault";
        this.dose=0;
        this.format="pardefauullt";
    }

    public Pill(String nom, int dose, String format, int ident) {
        this.nom = nom;
        this.dose = dose;
        this.format = format;
        this.ident=ident;
        frequence="";
        quand="";
    }
    public Pill(String nom, int dose, String format, int ident,String frequence,String quand) {
        this.nom = nom;
        this.dose = dose;
        this.format = format;
        this.ident = ident;
        this.frequence = frequence;
        this.quand = quand;
    }
    public Pill(String nom, int dose, String format,Bitmap image) {
        this.nom = nom;
        this.dose = dose;
        this.format = format;
        this.image=image;
        frequence="";
        quand="";
    }

    public Pill(Parcel in) {
        nom = in.readString();
        format = in.readString();
        dose = in.readInt();
        ident=in.readInt();
        image=(Bitmap)in.readParcelable(Bitmap.class.getClassLoader());
        frequence=in.readString();
        quand=in.readString();
    }

    public static final Creator<Pill> CREATOR = new Creator<Pill>() {
        @Override
        public Pill createFromParcel(Parcel in) {
            return new Pill(in);
        }

        @Override
        public Pill[] newArray(int size) {
            return new Pill[size];
        }
    };

    public int getIdent() {
        return ident;
    }

    public void setIdent(int id) {
        this.ident = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public String getQuand() {
        return quand;
    }

    public void setQuand(String quand) {
        this.quand = quand;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(format);
        dest.writeInt(dose);
        dest.writeInt(ident);
        dest.writeParcelable(image, flags);
        dest.writeString(frequence);
        dest.writeString(quand);
    }
}
