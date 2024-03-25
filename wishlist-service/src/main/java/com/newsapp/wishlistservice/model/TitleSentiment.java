package com.newsapp.wishlistservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleSentiment {
    @JsonProperty("sentiment")
    private String sentiment;

    @JsonProperty("score")
    private double score;
}
