package com.example.oussama.djahnit_application;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Posologie extends AppCompatActivity {
    Prescriptions prescriptions;
    private ListView list;
    private ListView list2;
    private ListView list3;
    private ListView list4;
    Prescriptions presActif;
    Pills pills;
    Pills medoc;
    int color1;
    int color2;
    int color3;
    int color4;
    private ArrayAdapter<Pill> adapterposologie;
    private ArrayAdapter<Pill> adapterposologie2;
    private ArrayAdapter<Pill> adapterposologie3;
    private ArrayAdapter<Pill> adapterposologie4;
    ArrayList<Pill> arrayposologie1 = new ArrayList<Pill>();
    ArrayList<Pill> arrayposologie2 = new ArrayList<Pill>();
    ArrayList<Pill> arrayposologie3 = new ArrayList<Pill>();
    ArrayList<Pill> arrayposologie4 = new ArrayList<Pill>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posologie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        presActif=new Prescriptions();
        pills=new Pills();
        medoc=new Pills();
        if(intent.hasExtra("pres")) {
            prescriptions = (Prescriptions) intent.getParcelableExtra("pres");


            for (Prescription p : prescriptions.getPrescriptions()) {
                if (p.isActif()) {
                    presActif.addPrescription(p);
                }
            }
            if (!presActif.getPrescriptions().isEmpty()) {
                for (Prescription p : presActif.getPrescriptions()) {
                        pills = p.getPills();
                    for (Pill m : pills.getPills()) {
                        medoc.addpills(m);

                    }

                }



                for (Pill pill : medoc.getPills()) {

                    if (pill.getFrequence().equals(getResources().getString(R.string.choice1)))//morning 8h
                    {
                        arrayposologie1.add(pill);
                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice2)))//noon 12h
                    {
                        arrayposologie2.add(pill);
                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice3)))//evening 20h
                    {

                        arrayposologie3.add(pill);
                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice4)))//night 22h
                    {
                        arrayposologie4.add(pill);
                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice5)))//morning+evening
                    {

                        arrayposologie1.add(pill);
                        arrayposologie3.add(pill);
                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice6)))//morning+night
                    {
                        arrayposologie1.add(pill);
                        arrayposologie4.add(pill);
                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice7)))//noon+evening
                    {
                        arrayposologie2.add(pill);
                        arrayposologie3.add(pill);
                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice8)))//noon+night
                    {
                        arrayposologie2.add(pill);
                        arrayposologie4.add(pill);
                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice9)))//morning+noon+evening
                    {
                        arrayposologie1.add(pill);
                        arrayposologie2.add(pill);
                        arrayposologie3.add(pill);
                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice10)))//noon+evening+night
                    {
                        arrayposologie2.add(pill);
                        arrayposologie3.add(pill);
                        arrayposologie4.add(pill);

                    }
                    if (pill.getFrequence().equals(getResources().getString(R.string.choice11)))//morning+noon+evening+night
                    {
                        arrayposologie1.add(pill);
                        arrayposologie2.add(pill);
                        arrayposologie3.add(pill);
                        arrayposologie4.add(pill);
                    }

                }




                adapterposologie=new PosologieAdapter(this, arrayposologie1);
                adapterposologie2=new PosologieAdapter(this, arrayposologie2);
                adapterposologie3=new PosologieAdapter(this, arrayposologie3);
                adapterposologie4=new PosologieAdapter(this, arrayposologie4);

                list = (ListView) findViewById(R.id.listposo);
                list2 = (ListView) findViewById(R.id.listposo2);
                list3 = (ListView) findViewById(R.id.listposo3);
                list4 = (ListView) findViewById(R.id.listposo4);
                list.setAdapter(adapterposologie);
                list2.setAdapter(adapterposologie2);
                list3.setAdapter(adapterposologie3);
                list4.setAdapter(adapterposologie4);

            }
            Calendar rightNow = Calendar.getInstance();
            int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY);
            if(!arrayposologie1.isEmpty()) {

                if (currentHourIn24Format >= 8 && currentHourIn24Format < 9) {
                    notificationapp(getApplicationContext());
                }}
            if(!arrayposologie2.isEmpty()) {
                if (currentHourIn24Format >= 12 && currentHourIn24Format <= 13) {
                    notificationapp(getApplicationContext());
                }
            }
            if(!arrayposologie3.isEmpty()) {
                if (currentHourIn24Format >= 20 && currentHourIn24Format < 21) {

                    notificationapp(getApplicationContext());
                }}
            if(!arrayposologie4.isEmpty()) {
                if (currentHourIn24Format >= 22 && currentHourIn24Format <= 23) {

                    notificationapp(getApplicationContext());
                }
            }




        }
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
    private class PosologieAdapter extends ArrayAdapter<Pill> {
        private class ViewHolder {
            TextView nom_ui;
            TextView dose_ui;
            TextView format_ui;
            TextView quand_ui;
        }

        public PosologieAdapter(@NonNull Context context, @NonNull List<Pill> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.posologie_item, null);
                holder = new ViewHolder();
                holder.nom_ui = (TextView) convertView.findViewById(R.id.nompill);
                holder.dose_ui=(TextView)  convertView.findViewById(R.id.dosepill);
                holder.format_ui=(TextView)  convertView.findViewById(R.id.formatpill);
                holder.quand_ui=(TextView)  convertView.findViewById(R.id.quandpill);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if(!presActif.getPrescriptions().isEmpty()) {
                Calendar rightNow = Calendar.getInstance();
                int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY);
                int couleur1;
                int couleur2;
                int couleur3;
                int couleur4;
                if(!adapterposologie.isEmpty()&&position<arrayposologie1.size()) {
                    Pill pill = adapterposologie.getItem(position);
                    holder.nom_ui.setText(pill.getNom());
                    holder.format_ui.setText(pill.getFormat());
                    String castaway = Integer.toString(pill.getDose()) + " mg";
                    holder.dose_ui.setText(castaway);
                    holder.quand_ui.setText(pill.getQuand());

                    if (currentHourIn24Format >= 8 && currentHourIn24Format < 9) {
                        color1 = R.color.now;
                    } else if (currentHourIn24Format >= 7 && currentHourIn24Format < 8) {
                        color1 = R.color.soon;
                    } else {
                        color1 = R.color.before;
                    }
                    if (Build.VERSION.SDK_INT >= 23) {
                        couleur1 = getResources().getColor(color1, null);
                    } else {
                        couleur1 = getResources().getColor(color1);
                    }
                    //convertView.setBackgroundColor(couleur1);
                    list.setBackgroundColor(couleur1);
                    adapterposologie.notifyDataSetChanged();
                }

                if(!arrayposologie2.isEmpty()&&position<arrayposologie2.size()) {

                        Pill pill2 = adapterposologie2.getItem(position);
                        holder.nom_ui.setText(pill2.getNom());
                        holder.format_ui.setText(pill2.getFormat());
                        String castaway = Integer.toString(pill2.getDose()) + " mg";
                        holder.dose_ui.setText(castaway);
                    holder.quand_ui.setText(pill2.getQuand());

                    if (currentHourIn24Format >= 12 && currentHourIn24Format <= 13) {
                        color2 = R.color.now;

                    } else if (currentHourIn24Format >= 11 && currentHourIn24Format < 12) {
                        color2 = R.color.soon;
                    } else {
                        color2 = R.color.before;
                    }

                    if (Build.VERSION.SDK_INT >= 23) {
                        couleur2 = getResources().getColor(color2, null);
                    } else {
                        couleur2 = getResources().getColor(color2);
                    }
                    //convertView.setBackgroundColor(couleur2);
                    list2.setBackgroundColor(couleur2);
                    adapterposologie2.notifyDataSetChanged();
                }
                if(!adapterposologie3.isEmpty()&&position<arrayposologie3.size()) {
                    Pill pill3 = adapterposologie3.getItem(position);
                    holder.nom_ui.setText(pill3.getNom());
                    holder.format_ui.setText(pill3.getFormat());
                    String castaway = Integer.toString(pill3.getDose()) + " mg";
                    holder.dose_ui.setText(castaway);
                    holder.quand_ui.setText(pill3.getQuand());

                    if (currentHourIn24Format >= 20 && currentHourIn24Format < 21) {
                        color3 = R.color.now;

                    } else if (currentHourIn24Format >= 19 && currentHourIn24Format < 20) {
                        color3 = R.color.soon;
                    } else {
                        color3 = R.color.before;
                    }

                    if (Build.VERSION.SDK_INT >= 23) {
                        couleur3 = getResources().getColor(color3, null);
                    } else {
                        couleur3 = getResources().getColor(color3);
                    }
                    //convertView.setBackgroundColor(couleur3);
                    list3.setBackgroundColor(couleur3);
                    adapterposologie3.notifyDataSetChanged();
                }
                if(!adapterposologie4.isEmpty()&&position<arrayposologie4.size()) {
                    Pill pill4 = adapterposologie4.getItem(position);
                    holder.nom_ui.setText(pill4.getNom());
                    holder.format_ui.setText(pill4.getFormat());
                    String castaway = Integer.toString(pill4.getDose()) + " mg";
                    holder.dose_ui.setText(castaway);
                    holder.quand_ui.setText(pill4.getQuand());
                    if (currentHourIn24Format >= 22 && currentHourIn24Format <= 23) {
                        color4 = R.color.now;

                    } else if (currentHourIn24Format >= 21 && currentHourIn24Format < 22) {
                        color4 = R.color.soon;
                    } else {
                        color4 = R.color.before;
                    }

                    if (Build.VERSION.SDK_INT >= 23) {
                        couleur4 = getResources().getColor(color4, null);
                    } else {
                        couleur4 = getResources().getColor(color4);
                    }
                    //convertView.setBackgroundColor(couleur4);
                    list4.setBackgroundColor(couleur4);
                    adapterposologie4.notifyDataSetChanged();
                }
            }
            return convertView;
        }

    }
    private NotificationManager notifManager;
    public void notificationapp(Context context) {
        final int NOTIFY_ID = 0; // ID of notification
        String id = context.getString(R.string.default_notification_channel_id); // default_channel_id
        String title = context.getString(R.string.default_notification_channel_title); // Default Channel
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (notifManager == null) {
            notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(getResources().getString(R.string.messagenotification))                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(context.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(getResources().getString(R.string.messagenotification))
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        }
        else {
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(getResources().getString(R.string.messagenotification))                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(context.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(getResources().getString(R.string.messagenotification))
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }
}


