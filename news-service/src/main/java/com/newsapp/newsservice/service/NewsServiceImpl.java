package com.newsapp.newsservice.service;

import com.newsapp.newsservice.model.NewsApiResponse;
import com.newsapp.newsservice.model.NewsArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@Slf4j
public class NewsServiceImpl implements NewsService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${newsapp.api.url}")
    private String URL;
    @Value("${newsapp.api.apikey}")
    private String xRapidApiKey ;
    @Value("${newsapp.api.apihost}")
    private String xRapidApiHost;
    @Override
    public List<NewsArticle> searchNewsArticles(String topic) {
        try{
            HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.set("X-RapidAPI-Key",xRapidApiKey);
            headers.set("X-RapidAPI-Host",xRapidApiHost);

            ResponseEntity<NewsApiResponse> response = restTemplate.exchange(
                    URL + topic,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    NewsApiResponse.class
            );
            log.info(response.toString());
            return response.getBody().getNews();
        }
        catch (Exception e){
            log.info("something is wrong");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Given Api key or Host are not valid..... :(",
                    e
            ) ;
        }
    }
}
