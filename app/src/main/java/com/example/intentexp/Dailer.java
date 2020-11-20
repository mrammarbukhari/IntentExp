package com.example.intentexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dailer extends AppCompatActivity {

    TextView txtNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailer);

        txtNum = (TextView) findViewById(R.id.txtNumber);
        Intent dialIntent = getIntent();
        Uri uri = dialIntent.getData();
        String telNumber = uri.toString();

        txtNum.setText(telNumber);




    }
}