package com.example.oussama.djahnit_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    static private final int PILLS_LIST = 1;
    static private final int MEDICAL_PRESCREPTION=2;
    private Pills pills;
    private Prescriptions prescriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(pills==null)
        {
            pills=new Pills();
            pills.populate(getResources().getString(R.string.choice5),getResources().getString(R.string.choicea));
        }
        if(prescriptions==null)
        {
            prescriptions=new Prescriptions();
            prescriptions.populate();
        }

        final Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Medicament_activity.class);
                intent.putExtra("array", pills);
                startActivityForResult(intent,PILLS_LIST);
            }
        });
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Prescription_activity.class);

                                intent.putExtra("array",pills);
                                intent.putExtra("pres",prescriptions);
                startActivityForResult(intent,MEDICAL_PRESCREPTION);
            }
        });
        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Posologie.class);


                intent.putExtra("pres",prescriptions);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PILLS_LIST && resultCode == RESULT_OK) {

            if (data.getExtras()==null) {
                Log.d(String.valueOf(MainActivity.this), "onActivityResult: exception while returning object");
            }
            else {
                pills = (Pills) data.getExtras().getParcelable("array");

            }
        }
        if (requestCode == MEDICAL_PRESCREPTION && resultCode == RESULT_OK) {

            if (data.getExtras()==null) {
                Log.d(String.valueOf(MainActivity.this), "onActivityResult: exception while returning object");
            }
            else {
                prescriptions = (Prescriptions) data.getExtras().getParcelable("pres");


            }
        }
    }
}
