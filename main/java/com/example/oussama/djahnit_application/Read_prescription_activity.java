package com.example.oussama.djahnit_application;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.List;

public class Read_prescription_activity extends AppCompatActivity {
    private Prescription prescription;
    private ListView list;
    private Pills pills;
    private ArrayAdapter<Pill> adapterprescription;
    private TextView date_ui;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_prescription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        prescription=(Prescription) intent.getParcelableExtra("prescription");
        pills=prescription.getPills();
        adapterprescription = new PrescAdapter(this, pills.getPills());
        list = (ListView) findViewById(R.id.list12);
        list.setAdapter(adapterprescription);
        date_ui=(TextView) findViewById(R.id.dateExpiration);
        date_ui.setText(prescription.getDate());

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
    private class PrescAdapter extends ArrayAdapter<Pill> {
         class ViewHolder {
            ImageView image_uii;
            TextView dose_ui;
            TextView nom_ui;
            TextView format_ui;
            TextView frequence_ui;
            TextView quand_ui;
        }

        public PrescAdapter(@NonNull Context context, @NonNull List<Pill> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.read_prescription_item, null);
                holder = new Read_prescription_activity.PrescAdapter.ViewHolder();
                holder.nom_ui = (TextView) convertView.findViewById(R.id.name);
                holder.dose_ui=(TextView)  convertView.findViewById(R.id.dose1);
                holder.format_ui=(TextView)  convertView.findViewById(R.id.format1);
                holder.frequence_ui=(TextView)  convertView.findViewById(R.id.frequence112);
                holder.quand_ui=(TextView)  convertView.findViewById(R.id.quand112);
                holder.image_uii=(ImageView) convertView.findViewById(R.id.imageview43);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Pill pill = getItem(position);
            holder.nom_ui.setText(pill.getNom());
            holder.format_ui.setText(pill.getFormat());
            String castaway=Integer.toString(pill.getDose())+" mg";
            holder.dose_ui.setText(castaway);
            if (pill.getImage()!=null){
                holder.image_uii.setImageBitmap(pill.getImage());
            }
            if (pill.getImage()==null){
                holder.image_uii.setImageResource(pill.getIdent());
            }
            holder.frequence_ui.setText(pill.getFrequence());
            holder.quand_ui.setText(pill.getQuand());
            adapterprescription.notifyDataSetChanged();
            return convertView;
        }
    }

}
