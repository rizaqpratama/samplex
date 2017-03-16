package com.t_kost.kost_tegal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Panduan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan);
        TextView tvHeader=(TextView)findViewById(R.id.tvHeader);
        tvHeader.setText(R.string.pandu);
        ImageButton btBack=(ImageButton) findViewById(R.id.btBack);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dari=getIntent().getExtras().getString("dari");
                assert dari != null;
                if(dari.equalsIgnoreCase("home")) {
                    startActivity(new Intent(Panduan.this, Home.class));
                    finish();
                }else
                {
                    startActivity(new Intent(Panduan.this, KostRinci.class));
                    finish();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {

    }
}
