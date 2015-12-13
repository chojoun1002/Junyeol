package com.example.user.junyeoljo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Day6_addschedule extends Activity {

    TextView textView01;
    EditText editText01;
    Button btn_ok, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day6_addschedule);
        editText01 = (EditText) findViewById(R.id.editText01);
        textView01 = (TextView) findViewById(R.id.textView01);
        textView01.setText((getIntent().getStringExtra("value") + " 일정등록하기"));
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Day6_addschedule.this,Day6_JJY.class);
                intent.putExtra("result",editText01.getText().toString());
                setResult(RESULT_OK,intent);
                //Toast.makeText(getApplicationContext(), editText01.getText().toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
