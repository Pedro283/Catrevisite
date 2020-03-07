package com.example.mycartevisite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView name;
Button email;
Button phone;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        Typeface custum_font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        name.setTypeface(custum_font);

        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + " +216 55758588"));
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"elkacimi.badr@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT,"Hello El KACIMI");
                email.putExtra(Intent.EXTRA_TEXT,"Cordialement") ;
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email,"Choose an Email client:"));

            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission( MainActivity.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                    startActivity(intent);
                    return;

                }else{
                    ActivityCompat.requestPermissions( MainActivity.this,new String []{Manifest.permission.CALL_PHONE},1);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if ( requestCode == 1){
            if (grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                startActivity(intent);

            }else{
                Toast.makeText(MainActivity.this, "Permission to denied to call phone", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
