package com.newsapp.wishlistservice.controller;

import com.newsapp.wishlistservice.exception.ArticleAlreadyWishlisted;
import com.newsapp.wishlistservice.exception.NoArticlesWishlisted;
import com.newsapp.wishlistservice.model.NewsArticle;
import com.newsapp.wishlistservice.model.WishList;
import com.newsapp.wishlistservice.service.AuthenticationServerFeignClient;
import com.newsapp.wishlistservice.service.WishListService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v1.0/wishlist"
)
@OpenAPIDefinition(
        info = @Info(
                title = "News App wishlist service API",
                description = "This API provides endpoints for wishlisting and getting wishlisted news articles based on users"
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Slf4j
public class NewsWishlistController {

    @Autowired
    private WishListService wishListervice;

    @Autowired
    private AuthenticationServerFeignClient feignClient;

    @PostMapping("/add")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<NewsArticle>> addArticleToWishList(@RequestBody NewsArticle article, @RequestHeader("Authorization") String token){
        try{
            log.info(feignClient.getUserName(token));
            String userName = feignClient.getUserName(token);
            WishList wishList = wishListervice.addArticleToWishList(userName,article);
            return new ResponseEntity<>(
                    wishList.getArticles(),
                    HttpStatus.CREATED
            );
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        } catch (ArticleAlreadyWishlisted e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<NewsArticle>> getWishListedArticles(@RequestHeader("Authorization") String token) throws NoArticlesWishlisted {
        String userName = feignClient.getUserName(token);
        log.info(userName);
        return new ResponseEntity<>(wishListervice.getWishListedArticles(userName),HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> deleteAnArticleFromWishlist(@RequestBody NewsArticle newsArticle, @RequestHeader("Authorization") String token) throws NoArticlesWishlisted {
        String userName = feignClient.getUserName(token);
        log.info("/remove request");
        log.info(userName);
        wishListervice.deleteAnArticleFromWishlist(newsArticle,userName);
        return new ResponseEntity<>(
                "Article removed from wishlist.",
                HttpStatus.OK
        );
    }
}
