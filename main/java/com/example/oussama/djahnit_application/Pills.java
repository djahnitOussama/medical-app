package com.example.oussama.djahnit_application;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Pills {
    private List<Pill> pills;

    public Pills() {

    }

    public void populate () {
        addpills (new Pill("mgdfgdg",3, "fefzdcc"));
        addpills (new Pill("mgdfgdg",1, "fefzdcc"));
        addpills (new Pill("mgdfgdg",2, "fefzdcc"));
        addpills (new Pill("mgdfgdg",6, "fefzdcc"));
        addpills (new Pill("mgdfgdg",1, "fefzdcc"));
        addpills (new Pill("mgdfgdg",4, "fefzdcc"));


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
}
