package com.example.user.junyeoljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Day4_subActivity extends ActionBarActivity {

    Button btn01;
    TextView textView01;
    EditText editText01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day4_sub);

        textView01 = (TextView) findViewById(R.id.textView01);
        textView01.setText(getIntent().getStringExtra("result") + " 화면");

        editText01 = (EditText) findViewById(R.id.editText01);

        btn01 = (Button) findViewById(R.id.btn01);
        btn01.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                if (!editText01.getText().toString().equals("")) {
                    intent.putExtra("result", editText01.getText().toString());
                } else {
                    intent.putExtra("result", "입력한 값이 없습니다.");
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}
