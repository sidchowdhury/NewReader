package com.example.sidc1.newreader;

import com.example.sidc1.newreader.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sidc1 on 15-10-2017.
 */

public class NewsStore {
    private static List<Article> newsArticles = new ArrayList<>();

    public static List<Article> getNewsArticles() {
        return newsArticles;
    }

    public static void setNewsArticles(List<Article> newsArticles) {
        NewsStore.newsArticles = newsArticles;
    }
}
