package com.example.oussama.djahnit_application;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Prescription_activity extends AppCompatActivity {
    Pills pills;
    Prescriptions prescriptions;
    private ListView list;
    private int color;
    private ArrayAdapter<Prescription> adapterprescription;
    static private final int CONTEXT_MENU_ACTIVATE =1;
    static private final int CONTEXT_MENU_DELETE =2;
    static private final int CONTEXT_MENU_UPDATE=5;
    static private final int ADD_PRESCRIPTION=3;
    static private final int UPDATE_PRESCRIPTION=4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();

        if(intent.getExtras()==null)
            {
                pills=new Pills();
                prescriptions=new Prescriptions();
                pills.populate(getResources().getString(R.string.choice5),getResources().getString(R.string.choicea));
               prescriptions.populate();
            } else {
                pills = (Pills) intent.getParcelableExtra("array");
                prescriptions=(Prescriptions) intent.getParcelableExtra("pres");


                    }


        adapterprescription = new PrescriptionAdapter(this, prescriptions.getPrescriptions());
        list = (ListView) findViewById(R.id.list2);

        list.setAdapter(adapterprescription);
        registerForContextMenu(list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent
                        = new Intent(Prescription_activity.this, Add_prescription_activity.class);
                intent.putExtra("array",pills);
                startActivityForResult(intent, ADD_PRESCRIPTION);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return onLongClick (parent, view, position, id);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent
                        = new Intent(Prescription_activity.this, Read_prescription_activity.class);
                Prescription prescription= (Prescription) list.getItemAtPosition(position);
                intent.putExtra("prescription",prescription);
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

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        //Context menu
        menu.setHeaderTitle("Choose an option");
        menu.add(Menu.NONE, CONTEXT_MENU_ACTIVATE, Menu.NONE, "Activate/Deactivate");
        menu.add(Menu.NONE, CONTEXT_MENU_DELETE, Menu.NONE, "Delete");
        menu.add(Menu.NONE, CONTEXT_MENU_UPDATE, Menu.NONE, "Update");
    }

    @Override
    public boolean onContextItemSelected (MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final Prescription p = (Prescription) list.getItemAtPosition(info.position);


        if(item.getItemId()==CONTEXT_MENU_ACTIVATE) {
                if(!prescriptions.getPrescriptionstate(p))
                {
                    prescriptions.setPrescriptionstate(p,true);
                    color=R.color.actif;
                }
                else {
                    prescriptions.setPrescriptionstate(p,false);
                    color=R.color.desactif;
                }

            }
            if(item.getItemId()== CONTEXT_MENU_DELETE) {
                prescriptions.removePrescription(p);

            }
        if(item.getItemId()== CONTEXT_MENU_UPDATE) {
            Intent intent
                    = new Intent(Prescription_activity.this, Add_prescription_activity.class);
            intent.putExtra("array",pills);
            intent.putExtra("prescription",p);
            long id=list.getItemIdAtPosition(info.position);
            intent.putExtra("idd",id);
            startActivityForResult(intent, UPDATE_PRESCRIPTION);

        }


        adapterprescription.notifyDataSetChanged();
        Intent intent2 =new Intent();
        intent2.putExtra("pres",prescriptions);
        setResult(Prescription_activity.RESULT_OK, intent2);
        return true;
    }
    private boolean onLongClick(AdapterView<?> parent, final View view, int position, long id) {
                registerForContextMenu(list);
                openContextMenu(list);
                adapterprescription.notifyDataSetChanged();
        return false;
    }

    private class PrescriptionAdapter extends ArrayAdapter<Prescription> {
        private class ViewHolder {
            TextView medoc_ui;
            TextView date_ui;
        }

        public PrescriptionAdapter(@NonNull Context context, @NonNull List<Prescription> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.prescription_item, null);
                holder = new ViewHolder();
                holder.medoc_ui = (TextView) convertView.findViewById(R.id.medoc);
                holder.date_ui=(TextView)  convertView.findViewById(R.id.dateE);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Prescription prescription= getItem(position);
            if (prescription.toString().isEmpty())
            {
                prescription.setPills(pills);
            }
            holder.medoc_ui.setText(prescription.toString());
            String strDate = prescription.getDate();
            holder.date_ui.setText(strDate);
            String presc_date=prescription.getDate();
            Date da= null;
            try {
                da = new SimpleDateFormat("dd/MM/yyyy",Locale.FRANCE).parse(presc_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date=new Date();

            if (date.after(da))
            {
                prescription.setActif(false);
            }

            int couleur;

                    if (!prescription.isActif()) {
                        color = R.color.desactif;
                    } else {
                        color = R.color.actif;

                    }
                    if (Build.VERSION.SDK_INT >= 23) {
                        couleur = getResources().getColor(color, null);
                    } else {
                        couleur = getResources().getColor(color);
                    }
                    convertView.setBackgroundColor(couleur);
                    adapterprescription.notifyDataSetChanged();
            return convertView;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PRESCRIPTION && resultCode == RESULT_OK) {
            Prescription p = data.getParcelableExtra("prescription");
                prescriptions.addPrescription(p);
            adapterprescription.notifyDataSetChanged();
        }
        if (requestCode == UPDATE_PRESCRIPTION && resultCode == RESULT_OK) {
            Prescription next = data.getParcelableExtra("prescription");
            long id = data.getLongExtra("idd",-1);
            Prescription old = prescriptions.getPrescriptions().get((int) id);
            prescriptions.updatePrescription(old, next);
            adapterprescription.notifyDataSetChanged();

        }
        Intent intent =new Intent();
        intent.putExtra("pres",prescriptions);
        setResult(Prescription_activity.RESULT_OK, intent);

    }
}
