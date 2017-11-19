package com.google.prototipo.seminarioii.celiacos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    ProgressBar barrita;
    int progreso = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {

                    barrita = (ProgressBar) findViewById(R.id.progressBar);
                    barrita.setVisibility(View.VISIBLE);
                    setProgresoBarrita(progreso);

                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), ChooserActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

    public void setProgresoBarrita(final int progreso) {
        barrita.setProgress(progreso);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgresoBarrita(progreso + 15);
            }
        });
        thread.start();
    }
}
