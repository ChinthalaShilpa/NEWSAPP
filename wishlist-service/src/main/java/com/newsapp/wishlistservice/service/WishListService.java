package com.newsapp.wishlistservice.service;

import com.newsapp.wishlistservice.exception.ArticleAlreadyWishlisted;
import com.newsapp.wishlistservice.exception.NoArticlesWishlisted;
import com.newsapp.wishlistservice.model.NewsArticle;
import com.newsapp.wishlistservice.model.WishList;

import java.util.List;

public interface WishListService {

    WishList addArticleToWishList(String userName, NewsArticle article) throws ArticleAlreadyWishlisted;
    List<NewsArticle> getWishListedArticles(String userName) throws NoArticlesWishlisted;

    WishList deleteAnArticleFromWishlist(NewsArticle newsArticle,String token) throws NoArticlesWishlisted;
}
