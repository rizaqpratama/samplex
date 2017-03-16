package com.t_kost.kost_tegal;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runThread();
    }

    private void runThread() {

        new Thread() {
            public void run() {
                int jh = 0;
                while (jh < 100) {
                    jh++;
                    try {
                        final int finalJh = jh;
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                            }
                        });
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        }.start();

    }
}
