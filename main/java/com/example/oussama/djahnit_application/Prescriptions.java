package com.example.oussama.djahnit_application;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Prescriptions implements Parcelable {
    private List<Prescription> prescriptions;


    public Prescriptions() {

    }
    private Prescriptions (Parcel in) {
        prescriptions = new ArrayList<Prescription>();
        in.readList(prescriptions, getClass().getClassLoader());

    }
    public static final Parcelable.Creator<Prescriptions> CREATOR = new Parcelable.Creator<Prescriptions>() {
        public Prescriptions createFromParcel(Parcel in) {
            return new Prescriptions(in);
        }

        @Override
        public Prescriptions[] newArray(int size) {
            return new Prescriptions[size];
        }
    };
    public void populate () {
        addPrescription (new Prescription(1));
        addPrescription (new Prescription(2));
        addPrescription (new Prescription(3));



    }

    public List<Prescription> getPrescriptions() {
        if (prescriptions == null) prescriptions = new ArrayList<>();
        return prescriptions;
    }
    public boolean getPrescriptionstate(Prescription p) {

        return p.isActif();
    }
    public boolean setPrescriptionstate(Prescription p,boolean actif) {

        return p.setActif(actif);
    }


    public void addPrescription (Prescription m) {
        getPrescriptions().add(m);
    }

    public void removePrescription (Prescription m) {
        getPrescriptions().remove(m);
    }

    public void updatePrescription (Prescription old, Prescription m) {
        getPrescriptions().remove(old);
        getPrescriptions().add(m);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(prescriptions);

    }
}
