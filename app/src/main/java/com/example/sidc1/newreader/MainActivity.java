package com.example.sidc1.newreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.sidc1.newreader.model.GetArticlesResponse;
import com.example.sidc1.newreader.networking.NewsAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRecyclerView = (RecyclerView)findViewById(R.id.activity_main_recycler_view);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.activity_main);

        Call<GetArticlesResponse> call = NewsAPI.getApi().getArticles("the-hindu", "top");
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {

                showNewsApiSnack();

                GetArticlesResponse getArticlesResponse = response.body();

                NewsStore.setNewsArticles(getArticlesResponse.getArticles());

                //Toast.makeText(MainActivity.this, "Response recieved", Toast.LENGTH_SHORT).show();

                HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(getArticlesResponse.getArticles(),MainActivity.this);
                newsRecyclerView.setAdapter(homeNewsAdapter);
            }

            @Override
            public void onFailure(Call<GetArticlesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error recieved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showNewsApiSnack(){
        Snackbar.make(coordinatorLayout, "Powered by NewsApi.org", Snackbar.LENGTH_LONG)
                .setAction("VISIT", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadNewsApiWebsite();
                    }
                }).show();
    }

    private void loadNewsApiWebsite(){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://newsapi.org")));
    }
}
