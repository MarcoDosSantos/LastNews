package com.example.recyclerviewudemy.model;

import java.util.List;

public class Response {
    private List<Article> articles;

    public Response(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
