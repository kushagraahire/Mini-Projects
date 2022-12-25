package com.company.newsx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.company.newsx.Interfaces.NewsInfoAPI;
import com.company.newsx.models.NewsAPIResponse;
import com.company.newsx.models.NewsHeadlines;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<NewsHeadlines> list;
    RecyclerView recyclerView;
    Adapter adapter;
    ProgressDialog dialog;
    Button btnGeneral, btnEntertainment, btnBusiness, btnHealth, btnScience, btnSports, btnTechnology;
    SearchView svNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGeneral = findViewById(R.id.btnGeneral);
        btnEntertainment = findViewById(R.id.btnEntertainment);
        btnBusiness = findViewById(R.id.btnBusiness);
        btnHealth = findViewById(R.id.btnHealth);
        btnScience = findViewById(R.id.btnScience);
        btnSports = findViewById(R.id.btnSports);
        btnTechnology = findViewById(R.id.btnTechnology);
        svNews = findViewById(R.id.svNews);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching NEWS Articles...");
        dialog.show();
        getNewsInfo("general",null);

        setClick();

        svNews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching NEWS Articles of "+query);
                dialog.show();
                getNewsInfo("general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void setClick() {
        btnBusiness.setOnClickListener(view -> {
            dialog.setTitle("Fetching NEWS Articles...");
            dialog.show();
            getNewsInfo("business",null);
        });

        btnGeneral.setOnClickListener(view -> {
            dialog.setTitle("Fetching NEWS Articles...");
            dialog.show();
            getNewsInfo("general",null);
        });

        btnEntertainment.setOnClickListener(view -> {
            dialog.setTitle("Fetching NEWS Articles...");
            dialog.show();
            getNewsInfo("entertainment",null);
        });

        btnHealth.setOnClickListener(view -> {
            dialog.setTitle("Fetching NEWS Articles...");
            dialog.show();
            getNewsInfo("health",null);
        });

        btnScience.setOnClickListener(view -> {
            dialog.setTitle("Fetching NEWS Articles...");
            dialog.show();
            getNewsInfo("science",null);
        });

        btnSports.setOnClickListener(view -> {
            dialog.setTitle("Fetching NEWS Articles...");
            dialog.show();
            getNewsInfo("sports",null);
        });

        btnTechnology.setOnClickListener(view -> {
            dialog.setTitle("Fetching NEWS Articles...");
            dialog.show();
            getNewsInfo("technology",null);
        });
    }

    private void getNewsInfo(String category, String query) {
        NewsInfoAPI newsInfoAPI = RequestManager.getClient().create(NewsInfoAPI.class);
        Call<NewsAPIResponse> call = newsInfoAPI.callHeadlines("in",category,query, "ceff8a48394d4a95b4ab2c3573a988c6");

        call.enqueue(new Callback<NewsAPIResponse>() {
            @Override
            public void onResponse(Call<NewsAPIResponse> call, Response<NewsAPIResponse> response) {
                if(response.isSuccessful()){
                    list = response.body().getArticles();
                    Log.e("TAG","List : "+list);
                    showNews(list);
                    dialog.dismiss();
                }else{
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsAPIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showNews(List<NewsHeadlines> list) {
        if(list.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        recyclerView = findViewById(R.id.recyclerMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new Adapter(this,list);
        recyclerView.setAdapter(adapter);
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}