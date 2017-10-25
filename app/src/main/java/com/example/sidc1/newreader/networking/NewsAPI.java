package com.example.sidc1.newreader.networking;

import com.example.sidc1.newreader.model.GetArticlesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sidc1 on 15-10-2017.
 */

public class NewsAPI {
    public static final String APIKEY = "4fd794a1cd074fb0954241d50208312f";
    public static final String APIPATH = "https://newsapi.org/v1/";
    private static NewsService newsService = null;

    public static NewsService getApi(){
        if (newsService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);
        }
        return newsService;
    }

    public interface NewsService{
        @GET("articles?apiKey=" + APIKEY)
        Call<GetArticlesResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);
    }

}
