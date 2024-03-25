package com.newsapp.newsservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsApiResponse {
    @JsonProperty("result")
    private Result result;

    @JsonProperty("news")
    private List<NewsArticle> news;
}


