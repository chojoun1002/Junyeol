package com.example.user.junyeoljo;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Day3_JJY extends ActionBarActivity {

    ImageView imageView01, imageView02;
    Button button_up, button_down, button_basic, button_back;
    BitmapDrawable bitmap01, bitmap02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day3);

        // 이미지 뷰 설정
        imageView01 = (ImageView) findViewById(R.id.imageView01);
        imageView02 = (ImageView) findViewById(R.id.imageView02);
        // 버튼 설정
        Button button_up = (Button) findViewById(R.id.button_up);
        Button button_down = (Button) findViewById(R.id.button_down);
        Button button_basic = (Button) findViewById(R.id.button_basic);
        Button button_back = (Button) findViewById(R.id.button_back);

        // 가로 스크롤뷰의 메소드 호출
        //scrollView01.setHorizontalScrollBarEnabled(true);

        // 이미지 가져오기
        Resources res = getResources();
        bitmap01 = (BitmapDrawable) res.getDrawable(R.drawable.img01);
        bitmap02 = (BitmapDrawable) res.getDrawable(R.drawable.img02);

        // 최초 이미지 세팅
        imageView01.setImageDrawable(bitmap01);
        imageView02.setImageDrawable(bitmap02);
        final String[] status = {"UD"};

        button_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이미지 UP!!", Toast.LENGTH_SHORT).show();
                if(status[0].equals("UD")) {
                    imageView01.setImageDrawable(bitmap02);
                    imageView02.setImageDrawable(bitmap02);
                    status[0] = "DD";
                }else if (status[0].equals("UU")) {
                    imageView01.setImageDrawable(bitmap01);
                    imageView02.setImageDrawable(bitmap02);
                    status[0] = "UD";
                }
            }
        });

        button_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이미지 Down!!", Toast.LENGTH_SHORT).show();
                if(status[0].equals("UD")) {
                    imageView01.setImageDrawable(bitmap01);
                    imageView02.setImageDrawable(bitmap01);
                    status[0] = "UU";
                }else if (status[0].equals("DD")) {
                    imageView01.setImageDrawable(bitmap01);
                    imageView02.setImageDrawable(bitmap02);
                    status[0] = "UD";
                }
            }
        });

        button_basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이미지 기본상태로!!", Toast.LENGTH_SHORT).show();
                imageView01.setImageDrawable(bitmap01);
                imageView02.setImageDrawable(bitmap02);
                status[0] = "UD";
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

}
