package com.t_kost.kost_tegal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    private Button btDaftar,btMap,btPanduan,btExit,btAbousUs;
    private AlertDialog alert = null;
    private AlertDialog.Builder alertDialogBuilder = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btDaftar=(Button)findViewById(R.id.btDaftar);
        btMap=(Button)findViewById(R.id.btMap);
        btAbousUs=(Button)findViewById(R.id.btAboutUs);
        btPanduan=(Button)findViewById(R.id.btPanduan);
        btExit=(Button)findViewById(R.id.btExit);
        btDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,DaftarKost.class));
                finish();
            }
        });
        btMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahkan=new Intent(Home.this,MapActivity.class);
                pindahkan.putExtra("dari","home");
                startActivity(pindahkan);
                finish();
            }
        });
        btPanduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahkan=new Intent(Home.this,Panduan.class);
                pindahkan.putExtra("dari","home");
                startActivity(pindahkan);
                finish();
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogBuilder = new AlertDialog.Builder(Home.this);
                alertDialogBuilder.setMessage("Anda yakin ingin Keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Batal",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        alert.cancel();
                                        dialog.cancel();
                                        alert.dismiss();

                                    }
                                });
                alertDialogBuilder.setNegativeButton("Keluar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                alert.cancel();
                                dialog.cancel();
                                alert.dismiss();
                                finish();
                                System.exit(0);
                            }
                        });
                alert = alertDialogBuilder.create();
                alert.show();
            }
        });

        btAbousUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,AboutUs.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
