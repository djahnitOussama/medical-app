package com.example.oussama.djahnit_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class Medicament_activity extends AppCompatActivity {
    static private final int ADD_PILL = 1;
    static private final int UPDATE_PILL = 2;
    private Pills pills;
    private ListView list;
    private ArrayAdapter<Pill> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();

        if(intent.getExtras() == null) {
            pills.populate(getResources().getString(R.string.choice5),getResources().getString(R.string.choicea));
            adapter = new medicamentAdapter(this, pills.getPills());
        } else {
            pills= (Pills) intent.getParcelableExtra("array");
            adapter = new medicamentAdapter(this, pills.getPills());
        }

            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Medicament_activity.this, Add_pill_Activity.class);
                Pill pill = (Pill) parent.getItemAtPosition(position);
                intent.putExtra("pill", pill);
                intent.putExtra("id", id);
                startActivityForResult(intent, UPDATE_PILL);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent
                        = new Intent(Medicament_activity.this, Add_pill_Activity.class);
                startActivityForResult(intent, ADD_PILL);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return onLongClick (parent, view, position, id);
            }
        });

    }
    private boolean onLongClick(AdapterView<?> parent, final View view, int position, long id) {
        final Pill p = (Pill) parent.getItemAtPosition(position);
        view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                pills.removeMedicaments(p);
                adapter.notifyDataSetChanged();
                view.setAlpha(1.0F);
            }
        });
        return false;
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

    class medicamentAdapter extends ArrayAdapter<Pill> {
        class ViewHolder {
            TextView nom_ui;
            TextView format_ui;
            TextView dose_ui;
            ImageView image_uii;
        }

        public medicamentAdapter(@NonNull Context context, @NonNull List<Pill> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.pills_item, null);
                holder = new ViewHolder();
                holder.nom_ui = (TextView) convertView.findViewById(R.id.nom);
                holder.format_ui = (TextView) convertView.findViewById(R.id.format);
                holder.dose_ui = (TextView) convertView.findViewById(R.id.dose);
                holder.image_uii=(ImageView)  convertView.findViewById(R.id.imageviewret);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Pill pill = getItem(position);
            holder.nom_ui.setText(pill.getNom());
            holder.format_ui.setText(pill.getFormat());
            String castaway=Integer.toString(pill.getDose());
            holder.dose_ui.setText(castaway+" mg");
            holder.image_uii.setImageBitmap(pill.getImage());
            if (pill.getImage()==null){
                holder.image_uii.setImageResource(pill.getIdent());
            }

            return convertView;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PILL && resultCode == RESULT_OK) {
            Pill m = data.getParcelableExtra("pill");
            pills.addpills(m);
            adapter.notifyDataSetChanged();
        }
        if (requestCode == UPDATE_PILL && resultCode == RESULT_OK) {
            Pill next = data.getParcelableExtra("pill");
            long id = data.getLongExtra("id", -1);
            Pill old = pills.getPills().get((int) id);
            pills.updateMedicaments(old, next);
            adapter.notifyDataSetChanged();

        }
        Intent intent =new Intent();
        intent.putExtra("array",pills);
        setResult(Medicament_activity.RESULT_OK, intent);
    }
}