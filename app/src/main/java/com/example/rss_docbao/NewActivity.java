package com.example.rss_docbao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    WebView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        view = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        String link = intent.getStringExtra("linktintuc");

        view.loadUrl(link);

    }
}