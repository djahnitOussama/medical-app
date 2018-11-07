package com.example.oussama.djahnit_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class Medicament_activity extends AppCompatActivity{
    static private final int ADD_PILL=1;
    static private final int UPDATE_PILL=2;
    private Pills pills;
    private ListView list;
    private ArrayAdapter<Pill> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pills = new Pills();
        pills.populate ();
        Log.d("Medicament_activity", pills.toString());
        adapter = new medicamentAdapter(this, pills.getPills());
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent
                        = new Intent(Medicament_activity.this, Add_pill_Activity.class);
                startActivityForResult(intent, ADD_PILL);
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
    private class medicamentAdapter extends ArrayAdapter<Pill> {

        public medicamentAdapter(@NonNull Context context, @NonNull List<Pill> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View root = inflater.inflate(R.layout.pills_item, null);

            TextView nom_ui = (TextView) root.findViewById(R.id.nom);
            TextView dose_ui = (TextView) root.findViewById(R.id.dose);
            TextView format_ui = (TextView) root.findViewById(R.id.format);

            Pill pill =getItem(position);
            nom_ui.setText(pill.getNom());
            String dose_st=Integer.toString(pill.getDose());
            dose_st =dose_st+" mg ";
            dose_ui.setText(dose_st);
            format_ui.setText(pill.getFormat());

            return root;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PILL && resultCode == RESULT_OK) {
            String nom = data.getStringExtra("nom");
            int dose = data.getIntExtra("dose", 0);
            String format = data.getStringExtra("format");

            Pill m = new Pill(nom,dose, format);
            pills.addpills(m);
            adapter.notifyDataSetChanged();
        }
    }
}
