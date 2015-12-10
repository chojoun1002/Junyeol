package com.example.user.junyeoljo;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Day5_viewPage extends LinearLayout {

    Context mContext;
    WebView webView;
    TextView textView01;
    Button button01;

    public Day5_viewPage(Context context) {
        super(context);

        init(context);
    }

    public Day5_viewPage(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        mContext = context;
        // inflate XML layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_day5_viewpage, this, true);
        webView = (WebView) findViewById(R.id.webview);
        button01 = (Button) findViewById(R.id.button01);
        textView01 = (TextView) findViewById(R.id.textView01);
        webView.setWebViewClient(new WebViewClient());
        WebSettings set = webView.getSettings();
        set.setJavaScriptEnabled(true);

        button01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(textView01.getText().toString());
            }
        });


    }

    public void setWebView(String resId) {
        webView.loadUrl(resId);
    }

}
