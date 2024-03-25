package com.newsapp.wishlistservice.controller;

import com.newsapp.wishlistservice.exception.ArticleAlreadyWishlisted;
import com.newsapp.wishlistservice.exception.NoArticlesWishlisted;
import com.newsapp.wishlistservice.model.NewsArticle;
import com.newsapp.wishlistservice.model.WishList;
import com.newsapp.wishlistservice.service.AuthenticationServerFeignClient;
import com.newsapp.wishlistservice.service.WishListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewsWishlistControllerTest {

    @Mock
    private WishListService wishListService;

    @Mock
    private AuthenticationServerFeignClient feignClient;

    @InjectMocks
    private NewsWishlistController wishlistController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getWishListedArticles_ValidToken_ReturnsWishListedArticles() throws NoArticlesWishlisted {
        // Arrange
        String token = "testToken";
        WishList wishList = new WishList();
        wishList.setArticles(new ArrayList<>());

        when(feignClient.getUserName(token)).thenReturn("testUser");
        when(wishListService.getWishListedArticles("testUser")).thenReturn(wishList.getArticles());

        // Act
        ResponseEntity<List<NewsArticle>> response = wishlistController.getWishListedArticles(token);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wishList.getArticles(), response.getBody());
        verify(feignClient, times(1)).getUserName(token);
        verify(wishListService, times(1)).getWishListedArticles("testUser");
    }

    @Test
    void getWishListedArticles_NoArticlesWishlisted_ThrowsNoArticlesWishlisted() throws NoArticlesWishlisted {
        // Arrange
        String token = "testToken";

        when(feignClient.getUserName(token)).thenReturn("testUser");
        when(wishListService.getWishListedArticles("testUser")).thenThrow
                (NoArticlesWishlisted.class);

        // Act & Assert
        assertThrows(NoArticlesWishlisted.class, () -> wishlistController.getWishListedArticles(token));
        verify(feignClient, times(1)).getUserName(token);
        verify(wishListService, times(1)).getWishListedArticles("testUser");
    }
}