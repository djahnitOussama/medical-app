package com.example.oussama.djahnit_application;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

 public class Pills implements Parcelable {
    private List<Pill> pills;

     public Pills() {
     }

     Pills(Parcel in) {
         pills = new ArrayList<Pill>();
         in.readList(pills, getClass().getClassLoader());
     }
     public static final Parcelable.Creator<Pills> CREATOR = new Parcelable.Creator<Pills>() {
         public Pills createFromParcel(Parcel in) {
             return new Pills(in);
         }

         @Override
         public Pills[] newArray(int size) {
             return new Pills[size];
         }
     };

     public void populate (String frequence,String quand) {

        addpills (new Pill("doliprane",1000, "capsule",R.mipmap.medoce_min,frequence,quand));
        addpills (new Pill("triatec",3, "capsule",R.mipmap.medoca_min,frequence,quand));
        addpills (new Pill("Cacit vitamine D3",1000, "tablet",R.mipmap.medocb_min,frequence,quand));
        addpills (new Pill("lantus Solostar",1000, "insulin",R.mipmap.medocc_min,frequence,quand));
        addpills (new Pill("clucophage",500, "capsule",R.mipmap.medocd_min,frequence,quand));



    }

    public List<Pill> getPills() {
        if (pills == null) pills = new ArrayList<>();
        return pills;
    }

    public void addpills (Pill m) {
        getPills().add(m);
    }

    public void removeMedicaments (Pill m) {
        getPills().remove(m);
    }

    public void updateMedicaments (Pill old, Pill m) {
        getPills().remove(old);
        getPills().add(m);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        if (getPills().isEmpty()) {
            tmp.append("empty");
        } else {
            int i = 0;
            for (Pill m : getPills()) {
                tmp.append("[");
                tmp.append(i++);
                tmp.append("] = ");
                tmp.append(m.toString());
                tmp.append("\n");
            }
        }
        return tmp.toString();
    }

     @Override
     public int describeContents() {
         return 0;
     }

     @Override
     public void writeToParcel(Parcel dest, int flags) {
         dest.writeList(pills);
     }
 }
