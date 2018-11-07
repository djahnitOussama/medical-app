package com.example.oussama.djahnit_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Add_pill_Activity extends AppCompatActivity {
    private EditText nom_ui;
    private EditText dose_ui;
    private EditText format_ui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nom_ui = (EditText) findViewById(R.id.nom);
        dose_ui = (EditText) findViewById(R.id.dose);
        format_ui = (EditText) findViewById(R.id.format);
        final Button button2 = (Button) findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAdd();
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
        switch (item.getItemId()) {
            case R.id.action_settings : return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void doAdd() {
        String nom = nom_ui.getText().toString();
        int dose = Integer.parseInt(dose_ui.getText().toString());
        String format = format_ui.getText().toString();

        Log.d("Add_pill_Activity", nom);
        Log.d("Add_pill_Activity", format);
        Log.d("Add", Integer.toString(dose));
        Intent intent = getIntent();
        intent.putExtra("nom", nom);
        intent.putExtra("dose", dose);
        intent.putExtra("format", format);
        setResult(RESULT_OK, intent);
        finish();
    }
}
