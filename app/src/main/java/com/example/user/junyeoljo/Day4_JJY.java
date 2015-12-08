package com.example.user.junyeoljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Day4_JJY extends ActionBarActivity {

    Button btn001, btn002, btn003, btn004;
    String msg ="";
    final int REQUEST_CODE0 = 1002;
    final int REQUEST_CODE1 = 1003;
    final int REQUEST_CODE2 = 1004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day4);

        btn001 = (Button) findViewById(R.id.btn001);
        btn002 = (Button) findViewById(R.id.btn002);
        btn003 = (Button) findViewById(R.id.btn003);
        btn004 = (Button) findViewById(R.id.btn004);

        btn001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(Day4_JJY.this, Day4_subActivity.class));
                intent.putExtra("result", btn001.getText().toString());
                startActivityForResult(intent, REQUEST_CODE0);

            }
        });

        btn002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(Day4_JJY.this, Day4_subActivity.class));
                intent.putExtra("result", btn002.getText().toString());
                startActivityForResult(intent, REQUEST_CODE1);
            }
        });

        btn003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(Day4_JJY.this, Day4_subActivity.class));
                intent.putExtra("result", btn003.getText().toString());
                startActivityForResult(intent, REQUEST_CODE2);
            }
        });

        btn004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE0:
                if(resultCode == RESULT_OK){
                    msg = data.getStringExtra("result");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE1:
                if(resultCode == RESULT_OK){
                    msg = data.getStringExtra("result");
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE2:
                if(resultCode == RESULT_OK){
                    msg = data.getStringExtra("result");
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 대화상자 객체 생성
     private AlertDialog createDialogBox(String Title){
     AlertDialog.Builder builder  = new AlertDialog.Builder(Day4_JJY.this);

     builder .setTitle(Title);
     builder .setMessage(Title+" 화면을 종료하시겠습니까?");
     builder .setIcon(android.R.drawable.ic_dialog_alert);

     // 예 버튼 설정
     builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
     public void onClick(DialogInterface dialog, int whichButton) {
     msg = "예 버튼이 눌렀습니다. ";
     Toast.makeText(Day4_JJY.this, msg, Toast.LENGTH_SHORT).show();
     //finish();
     }
     });

     // 아니오 버튼 설정
     builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
     public void onClick(DialogInterface dialog, int whichButton) {
     msg = "아니오 버튼이 눌렸습니다. ";
     Toast.makeText(Day4_JJY.this, msg, Toast.LENGTH_SHORT).show();
     }
     });

     // 빌더 객체의 create() 메소드 호출하면 대화상자 객체 생성
     AlertDialog dialog = builder.create();
     return dialog;
     }
     */

}
