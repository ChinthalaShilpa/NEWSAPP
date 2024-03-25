package com.newsapp.wishlistservice.service;

import com.newsapp.wishlistservice.exception.ArticleAlreadyWishlisted;
import com.newsapp.wishlistservice.exception.NoArticlesWishlisted;
import com.newsapp.wishlistservice.model.NewsArticle;
import com.newsapp.wishlistservice.model.WishList;
import com.newsapp.wishlistservice.repository.WishlistRepository;
import com.newsapp.wishlistservice.service.AuthenticationServerFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishListServiceImplTest {

    @Mock
    private AuthenticationServerFeignClient feignClient;

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishListServiceImpl wishListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addArticleToWishList_ArticleNotWishlisted_AddsArticleToWishList() throws ArticleAlreadyWishlisted {
        // Arrange
        String userName = "testUser";
        NewsArticle article = new NewsArticle();
        article.setTitle("Test Article");

        WishList wishList = new WishList();
        wishList.setUserName(userName);
        wishList.setArticles(new ArrayList<>());

        when(wishlistRepository.findByUserName(userName)).thenReturn(Optional.of(wishList));
        when(wishlistRepository.save(wishList)).thenReturn(wishList);

        // Act
        WishList result = wishListService.addArticleToWishList(userName, article);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getArticles().size());
        assertEquals(article, result.getArticles().get(0));
        verify(wishlistRepository, times(1)).findByUserName(userName);
        verify(wishlistRepository, times(1)).save(wishList);
    }

    @Test
    void addArticleToWishList_ArticleAlreadyWishlisted_ThrowsArticleAlreadyWishlisted() {
        // Arrange
        String userName = "testUser";
        NewsArticle article = new NewsArticle();
        article.setTitle("Test Article");

        WishList wishList = new WishList();
        wishList.setUserName(userName);
        wishList.setArticles(List.of(article));

        when(wishlistRepository.findByUserName(userName)).thenReturn(Optional.of(wishList));

        // Act & Assert
        assertThrows(ArticleAlreadyWishlisted.class, () -> wishListService.addArticleToWishList(userName, article));
        verify(wishlistRepository, times(1)).findByUserName(userName);
        verify(wishlistRepository, never()).save(wishList);
    }

    @Test
    void getWishListedArticles_WishListExists_ReturnsWishListedArticles() throws NoArticlesWishlisted {
        // Arrange
        String userName = "testUser";
        NewsArticle article1 = new NewsArticle();
        article1.setTitle("Test Article 1");
        NewsArticle article2 = new NewsArticle();
        article2.setTitle("Test Article 2");

        WishList wishList = new WishList();
        wishList.setUserName(userName);
        wishList.setArticles(List.of(article1, article2));

        when(wishlistRepository.findByUserName(userName)).thenReturn(Optional.of(wishList));

        // Act
        List<NewsArticle> result = wishListService.getWishListedArticles(userName);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(article1, result.get(0));
        assertEquals(article2, result.get(1));
        verify(wishlistRepository, times(1)).findByUserName(userName);
    }

    @Test
    void getWishListedArticles_WishListDoesNotExist_ThrowsNoArticlesWishlisted() {
        // Arrange
        String userName = "testUser";

        when(wishlistRepository.findByUserName(userName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoArticlesWishlisted.class, () -> wishListService.getWishListedArticles(userName));
        verify(wishlistRepository, times(1)).findByUserName(userName);
    }
}