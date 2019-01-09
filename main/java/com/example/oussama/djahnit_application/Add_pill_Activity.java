package com.example.oussama.djahnit_application;

import android.content.Intent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static com.example.oussama.djahnit_application.R.mipmap.defaut_bitmap;


public class Add_pill_Activity extends AppCompatActivity {
    private static final int PICTURE_TAKEN = 1;
    private static final int PICTURE_CHOOSEN = 2;
    private EditText nom_ui;
    private EditText dose_ui;
    private EditText format_ui;
    private ImageView image_ui;
    private Bitmap image_bm;
    private Bitmap b;
    private int typeimage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nom_ui = (EditText) findViewById(R.id.nom);
        dose_ui = (EditText) findViewById(R.id.dose);
        format_ui = (EditText) findViewById(R.id.format);
        image_ui = (ImageView) findViewById(R.id.imageview);
        image_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeimage = 1;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PICTURE_TAKEN);

            }
        });
        final Button button2 = (Button) findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAdd();
            }
        });
        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeimage = 2;
                Intent myintent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                myintent.setType("image/*");
                myintent.putExtra("crop", "true");
                myintent.putExtra("scale", true);
                myintent.putExtra("outputX", 256);
                myintent.putExtra("outputY", 256);
                myintent.putExtra("aspectX", 1);
                myintent.putExtra("aspectY", 1);
                myintent.putExtra("return-data", true);
                //Intent myintent = new Intent();
                //myintent.setType("image/*");
                //myintent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(myintent,PICTURE_CHOOSEN);
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
            case R.id.action_settings:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void doAdd() {

        if(nom_ui.getText().toString().isEmpty() | dose_ui.getText().toString().isEmpty()|format_ui.getText().toString().isEmpty()) {
            setResult(RESULT_CANCELED);
            finish();
        }
        else {
            String nom = nom_ui.getText().toString();
            int dose = Integer.parseInt(dose_ui.getText().toString());
            String format = format_ui.getText().toString();
            int ident = defaut_bitmap;
            Pill p;
            if (typeimage == 1) {
                Bitmap image = image_bm;
                p = new Pill(nom, dose, format, image);
            } else if (typeimage == 2) {
                Bitmap image = b;
                p = new Pill(nom, dose, format, image);
            } else {
                ImageView image = (ImageView) findViewById(R.id.imageview);
                //Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.defaut_bitmap);
                //image.setImageBitmap(b);
                image.setImageResource(ident);
                p = new Pill(nom, dose, format, ident);

            }

            Intent intent = getIntent();
            intent.putExtra("pill", p);
            setResult(RESULT_OK, intent);
            finish();
        }
     }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_TAKEN &&resultCode==RESULT_OK) {
            ////return image=bitmap;
            image_bm = (Bitmap) data.getExtras().get("data");
            image_ui.setImageBitmap(image_bm);
        }
        if (requestCode == PICTURE_CHOOSEN&&resultCode==RESULT_OK) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap newProfilePic = extras.getParcelable("data");
                b = newProfilePic;
                image_ui.setImageBitmap(b);

            }
        }

    }
}
