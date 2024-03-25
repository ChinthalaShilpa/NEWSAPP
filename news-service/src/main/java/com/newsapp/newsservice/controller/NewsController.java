package com.newsapp.newsservice.controller;

import com.newsapp.newsservice.exception.NoArticlesFoundException;
import com.newsapp.newsservice.model.NewsApiResponse;
import com.newsapp.newsservice.model.NewsArticle;
import com.newsapp.newsservice.service.NewsServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/news")
@OpenAPIDefinition(
        info = @Info(
                title = "News App news provider server API",
                description = "This API provides endpoints for getting the news based user intrests."
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class NewsController {

    @Autowired
    private NewsServiceImpl newsService;

    @GetMapping(
            path = "/search/{topic}"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<NewsArticle>> searchNewsArticles(@PathVariable("topic") String topic) throws NoArticlesFoundException {

        List<NewsArticle> newsArticlesFound = newsService.searchNewsArticles(topic);

        if(newsArticlesFound.size() == 0){
            throw new NoArticlesFoundException("No articles found with the given topic " + topic);
        }
        return new ResponseEntity<>(
                newsArticlesFound,
                HttpStatus.OK
        );
    }
}
