package com.example.yjh.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        NewsContentFragment newsContentFragment = (NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(title, content);
    }

    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("title", newsTitle);
        intent.putExtra("content", newsContent);
        context.startActivity(intent);
    }

}
