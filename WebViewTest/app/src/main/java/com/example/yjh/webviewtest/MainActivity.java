package com.example.yjh.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.wv_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
//        此行代码的作用是当打开另一个网页时不跳出到浏览器显示
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.baidu.com");
    }
}
