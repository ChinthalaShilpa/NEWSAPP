package com.newsapp.wishlistservice.model;

import com.newsapp.wishlistservice.model.NewsArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishList")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishList {
    @Id
    private String id;
    private String userName;
    private List<NewsArticle> articles;
}
