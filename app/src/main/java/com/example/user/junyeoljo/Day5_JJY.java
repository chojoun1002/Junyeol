package com.example.user.junyeoljo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class Day5_JJY extends ActionBarActivity {

    private WebView webView;
    Button button01;
    TextView textView01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day5);

        webView = (WebView)findViewById(R.id.webView);
        button01 = (Button) findViewById(R.id.button01);
        textView01 = (TextView) findViewById(R.id.textView01);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(textView01.getText().toString());
            }
        });



    }

}
