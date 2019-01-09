package com.example.oussama.djahnit_application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Add_prescription_activity extends AppCompatActivity {
    Pills pills;
    String spinnerchoice;
    String spinnerchoice2;
    String spinnerchoice3;
    EditText dateE;
    boolean isValid ;
    TextView listpills;
    Pills prescPills;
    Prescription prescrip;
    ArrayList <String>prescadd;
    int i = -1;
    long id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        prescadd=new ArrayList<String>();
        listpills=(TextView) findViewById(R.id.listpills);
        if(intent.getExtras()==null)
        {
            Log.d(String.valueOf(Add_prescription_activity.this), "error parse");
        }
        pills = (Pills) intent.getParcelableExtra("array");
        final List<String> spinnerArray = new ArrayList<>();
        //String txtpill =getString(R.string.spinner_title);
        //spinnerArray.add(txtpill);
        for (Pill m : pills.getPills()) {
            spinnerArray.add(m.getNom());
        }
        prescPills=new Pills();

        if(intent.getParcelableExtra("prescription")==null)
        {
            prescrip=new Prescription();
        }
        else
        {
            prescrip=(Prescription)intent.getParcelableExtra("prescription");
            Pills p;
            p=prescrip.getPills();
            for(Pill m:p.getPills())
            {
                prescadd.add(m.getNom());
                prescPills.addpills(m);
                listpills.setText(prescadd.toString());
                i++;
            }
        }

        intent.getLongExtra("idd",id);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                spinnerchoice=adapterView.getSelectedItem().toString();
                if (item != null) {
                    Toast.makeText(Add_prescription_activity.this, item.toString(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Add_prescription_activity.this, "Selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

        List<String> spinnerArray2 = new ArrayList<>();
        spinnerArray2.add(getString(R.string.choice1));
        spinnerArray2.add(getString(R.string.choice2));
        spinnerArray2.add(getString(R.string.choice3));
        spinnerArray2.add(getString(R.string.choice4));
        spinnerArray2.add(getString(R.string.choice5));
        spinnerArray2.add(getString(R.string.choice6));
        spinnerArray2.add(getString(R.string.choice7));
        spinnerArray2.add(getString(R.string.choice8));
        spinnerArray2.add(getString(R.string.choice9));
        spinnerArray2.add(getString(R.string.choice10));
        spinnerArray2.add(getString(R.string.choice11));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerArray2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner3);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                spinnerchoice2=adapterView.getSelectedItem().toString();
                if (item != null) {
                    Toast.makeText(Add_prescription_activity.this, item.toString(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Add_prescription_activity.this, "Selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

        List<String> spinnerArray3 = new ArrayList<>();
        spinnerArray3.add(getString(R.string.choicea));
        spinnerArray3.add(getString(R.string.choiceb));
        spinnerArray3.add(getString(R.string.choicec));

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerArray3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner4);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                spinnerchoice3=adapterView.getSelectedItem().toString();
                if (item != null) {
                    Toast.makeText(Add_prescription_activity.this, item.toString(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Add_prescription_activity.this, "Selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });


        dateE = (EditText) findViewById(R.id.dateEx);
        dateE.addTextChangedListener(mDateEntryWatcher);

        final Button submit=(Button) findViewById(R.id.button5);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!prescadd.isEmpty()) {
                        if (spinnerchoice != prescadd.get(i)) {
                            doAdd();
                        }
                    }

                else{
                    doAdd();
                }
            }
        });
        final Button done=(Button) findViewById(R.id.button6);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid) {
                    if (!prescadd.isEmpty()) {


                        if (spinnerchoice != prescadd.get(i)) {
                            Log.d(String.valueOf(Add_prescription_activity.this), prescadd.get(i));
                            doAdd();
                        }

                    } else {
                        doAdd();
                    }
                    Intent myintent = getIntent();
                    //intent.putExtra("prescpills", prescPills);
                    myintent.putExtra("prescription", prescrip);
                    myintent.putExtra("idd", id);

                    setResult(RESULT_OK, myintent);

                finish();}
            }
        });

    }
    private TextWatcher mDateEntryWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String working = s.toString();
            isValid = true;
            if (working.length()==2 && before ==0) {
                if (Integer.parseInt(working) < 1 || Integer.parseInt(working)>12) {
                    isValid = false;
                } else {
                    working+="/";
                    dateE.setText(working);
                    dateE.setSelection(working.length());
                }
            }
            else if (working.length()==10 && before ==0) {
                String enteredYear = working.substring(3);
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                if (Integer.parseInt(enteredYear) < currentYear) {
                    isValid = false;
                }
            } else if (working.length()!=10) {
                isValid = false;
            }

            if (!isValid) {
                dateE.setError("Enter a valid date: dd/MM/YYYY");
            } else {
                dateE.setError(null);
            }

        }


        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    };
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void doAdd() {

        for (Pill p : pills.getPills()) {
            if(p.getNom()==spinnerchoice)
            {
                prescadd.add(spinnerchoice);
                p.setFrequence(spinnerchoice2);
                p.setQuand(spinnerchoice3);
                prescPills.addpills(p);
            }
        }
        String dateExp=dateE.getText().toString();
        prescrip.setDate(dateExp);
        prescrip.setPills(prescPills);
        i++;
        listpills.setText( prescadd.toString());
    }

}
