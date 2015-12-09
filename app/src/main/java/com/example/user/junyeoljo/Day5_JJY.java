package com.example.user.junyeoljo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Day5_JJY extends ActionBarActivity {

    private WebView webView;
    Button button01;
    EditText editText01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day5);

        webView = (WebView)findViewById(R.id.webView);
        button01 = (Button) findViewById(R.id.button01);
        editText01 = (EditText) findViewById(R.id.editText01);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),editText01.getText().toString(),Toast.LENGTH_SHORT).show();
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(editText01.getText().toString());
            }
        });



    }

}
