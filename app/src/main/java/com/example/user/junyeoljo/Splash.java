package com.example.user.junyeoljo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

public class Splash extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
                // 3 초후 이미지를 닫아버림
            }
        }, 3000);

    }

}
