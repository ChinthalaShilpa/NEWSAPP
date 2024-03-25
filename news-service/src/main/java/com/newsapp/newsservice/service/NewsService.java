package com.newsapp.newsservice.service;

import com.newsapp.newsservice.model.NewsArticle;

import java.util.List;

public interface NewsService {

    List<NewsArticle> searchNewsArticles(String topic);
}
