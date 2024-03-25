package com.newsapp.wishlistservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @JsonProperty("response")
    private String response;

    @JsonProperty("newsCount")
    private int newsCount;

    @JsonProperty("skipped")
    private int skipped;
}
