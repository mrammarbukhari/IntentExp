package com.example.intentexp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnExp, btnImp, btnShare, btnSearch, btnView;
    EditText txtSearch, txtLat, txtLong;

    Intent dial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExp = (Button) findViewById(R.id.btnExp);
        btnImp = (Button) findViewById(R.id.btnImp);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnView = (Button) findViewById(R.id.btnShow);

        txtSearch = (EditText) findViewById(R.id.txtSearch);
        txtLat = (EditText) findViewById(R.id.txtLat);
        txtLong = (EditText) findViewById(R.id.txtLong);

        btnExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction("com.example.intentexp.MYDAILER");
                intent.setData(Uri.parse("tel:+923239586191"));
                startActivity(intent);
            }
        });
        btnImp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial = new Intent();
                dial.setAction(Intent.ACTION_CALL);
                dial.setData(Uri.parse("tel:+923239586191"));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED)   //check self permission
                {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE))
                    {
                        showAlertDialog("Call permission information","This information is required");
                    }
                    else
                    {
                        requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 00);
                    }
                }
                else
                {
                    startActivity(dial);
                }
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareintent = new Intent();
                shareintent.setAction(Intent.ACTION_SEND);
                shareintent.putExtra(Intent.EXTRA_TEXT,"Hello World");
                shareintent.setType("text/plain");
                startActivity(shareintent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent  = new Intent();
                String Query  = txtSearch.getText().toString();
                searchIntent.setAction(Intent.ACTION_WEB_SEARCH);
                searchIntent.putExtra(SearchManager.QUERY,Query);
                startActivity(searchIntent);
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cord = "geo:"+txtLat.getText().toString() + ","+txtLong.getText().toString();
                Intent geo = new Intent();
                geo.setAction(Intent.ACTION_VIEW);
                //COMSATS Lat: 34.198879, Long: 73.243451
                //geo:34.198879,73.243451?q=COMSATS+Univeristy+Islamabad,+Abbottabad+Campus
                geo.setData(Uri.parse(cord+"?q=COMSATS+Univeristy+Islamabad,+Abbottabad+Campus"));
                startActivity(geo);
            }
        });



    }

    public void showAlertDialog(String title, String text)
    {
        // why this information is required
        AlertDialog.Builder diag = new AlertDialog.Builder(this);
        diag.setPositiveButton(R.string.DialogBoxOK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 00);
            }
        });
        diag.setNegativeButton("Cancel",null);
        diag.setTitle(title);
        diag.setMessage(text);
        diag.setCancelable(true);

        AlertDialog alert = diag.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 00)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                startActivity(dial);
            }
            else
            {
                showAlertDialog("Call Information", "This information is necessary to call");
            }
        }
    }

}