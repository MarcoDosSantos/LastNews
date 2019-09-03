package com.example.recyclerviewudemy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerviewudemy.utils.Adapter;
import com.example.recyclerviewudemy.model.Article;
import com.example.recyclerviewudemy.utils.NewsRetrofitService;
import com.example.recyclerviewudemy.R;
import com.example.recyclerviewudemy.model.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Article> articles;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private View.OnClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        articles = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);

        llenarLista();


        Adapter adapter = new Adapter(getApplicationContext(), articles);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Has elegido " +
                        articles.get(recyclerView.getChildAdapterPosition(view)).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    /*public Article(String name, String author, String title, String description, String url,
                   String urlToImage, String publishedAt, String content)*/

    private void llenarLista() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsRetrofitService service = retrofit.create(NewsRetrofitService.class);
        Call<Response> callNews = service.getArticles("ar", "985................3f1");

        callNews.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    articles = response.body().getArticles();
                    Adapter adapter = new Adapter(getApplicationContext(), articles);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            /*Toast.makeText(MainActivity.this, "Has elegido " +
                                    articles.get(recyclerView.getChildAdapterPosition(view)).getTitle(), Toast.LENGTH_SHORT).show();*/
                            String url = articles.get(recyclerView.getChildAdapterPosition(view)).getUrl();
                            Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("url", url);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure " + t.getMessage(), Toast.LENGTH_LONG).show();
            }


        });


    }


}
