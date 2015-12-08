package com.example.user.junyeoljo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

    Button button01;
    EditText idText, pwText;
    String id = "20151044", pw = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idText = (EditText) findViewById(R.id.idText);
        pwText = (EditText) findViewById(R.id.pwText);

        button01 = (Button) findViewById(R.id.btnLogin);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!idText.getText().toString().equals(id)) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력 하여 주세요.", Toast.LENGTH_SHORT).show();
                }
                if (!pwText.getText().toString().equals(pw)) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력 하여 주세요.", Toast.LENGTH_SHORT).show();
                }
                if (idText.getText().toString().equals(id) && pwText.getText().toString().equals(pw)) {
                    setResult(RESULT_OK);
                    finish();

                }
            }
        });

    }

}
