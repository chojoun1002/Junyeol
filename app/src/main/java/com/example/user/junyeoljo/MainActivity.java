package com.example.user.junyeoljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button button01, button02,button03, button04, button05, button06;
    final int REQUEST_CODE = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, Splash.class));

        //button01 = (Button) findViewById(R.id.button01);
        button01 = (Button) findViewById(R.id.button01);
        button01.setOnClickListener(this);
        button02 = (Button) findViewById(R.id.button02);
        button02.setOnClickListener(this);
        button03 = (Button) findViewById(R.id.button03);
        button03.setOnClickListener(this);
        button04 = (Button) findViewById(R.id.button04);
        button04.setOnClickListener(this);
        button05 = (Button) findViewById(R.id.button05);
        button05.setOnClickListener(this);
        button06 = (Button) findViewById(R.id.button06);
        button06.setOnClickListener(this);
        //button06.setVisibility(View.INVISIBLE);

//        button01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Day2_JJY.class);
//                startActivity(intent);
//
//            }
//        });
//
//        button02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Day3_JJY.class);
//                startActivity(intent);
//
//            }
//        });
//
//        button03.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Intent intent=new Intent(MainActivity.this,Day4_JJY.class);
//                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
//
//            }
//        });
//
//        button04.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Day5_JJY.class);
//                startActivity(intent);
//
//            }
//        });
//
//        button05.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Day6_JJY.class);
//                startActivity(intent);
//
//            }
//        });
//
//        button06.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Day5_JJY.class);
//                startActivity(intent);
//
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "[조준열]님 로그인을 환영합니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Day4_JJY.class));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button01 : {
                Intent intent = new Intent(MainActivity.this, Day2_JJY.class);
                startActivity(intent);
            }
            break;
            case R.id.button02 : {
                Intent intent=new Intent(MainActivity.this,Day3_JJY.class);
                startActivity(intent);
            }
            break;
            case R.id.button03 : {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
            break;
            case R.id.button04 : {
                Intent intent=new Intent(MainActivity.this,Day5_JJY.class);
                startActivity(intent);
            }
            break;
            case R.id.button05 : {
                Intent intent=new Intent(MainActivity.this,Day6_JJY.class);
                startActivity(intent);
            }
            break;
            case R.id.button06 : {
                Intent intent=new Intent(MainActivity.this,Day7_JJY.class);
                startActivity(intent);
            }
            break;
        }
    }
}
