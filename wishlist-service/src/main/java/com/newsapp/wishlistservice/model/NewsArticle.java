package com.newsapp.wishlistservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsArticle {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Url")
    private String url;

    @JsonProperty("PublishedOn")
    private Date publishedOn;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Language")
    private String language;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("SourceNationality")
    private String sourceNationality;

    @JsonProperty("TitleSentiment")
    private TitleSentiment titleSentiment;

    @JsonProperty("Summary")
    private String summary;

    @JsonProperty("Countries")
    private List<String> countries;

    @JsonProperty("Categories")
    private Categories categories;

    // getters and setters

    @Override
    public String toString() {
        return "NewsArticle{" +
                "title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                ", publishedOn=" + publishedOn +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", image='" + image + '\'' +
                ", sourceNationality='" + sourceNationality + '\'' +
                ", titleSentiment=" + titleSentiment +
                ", summary='" + summary + '\'' +
                ", countries=" + countries +
                ", categories=" + categories +
                '}';
    }
}
