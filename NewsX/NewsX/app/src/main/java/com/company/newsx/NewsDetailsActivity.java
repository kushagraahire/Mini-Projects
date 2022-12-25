package com.company.newsx;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.newsx.models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
    TextView tvDetailsTitle, tvDetailsAuthor, tvDetailsTime, tvDetailsContent, tvDetailsDetail;
    ImageView ivDetails;
    NewsHeadlines headlines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("newsData");

        tvDetailsAuthor = findViewById(R.id.tvDetailsAuthor);
        tvDetailsTime = findViewById(R.id.tvDetailsTime);
        tvDetailsContent = findViewById(R.id.tvDetailsContent);
        tvDetailsDetail = findViewById(R.id.tvDetailsDetail);
        tvDetailsTitle = findViewById(R.id.tvDetailsTitle);
        ivDetails = findViewById(R.id.ivDetails);

        tvDetailsTitle.setText(headlines.getTitle());
        tvDetailsDetail.setText(headlines.getDescription());
        tvDetailsTime.setText(headlines.getPublishedAt());
        tvDetailsAuthor.setText(headlines.getAuthor());
        tvDetailsContent.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(ivDetails);

    }
}