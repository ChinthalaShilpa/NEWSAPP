package com.newsapp.wishlistservice.repository;

import com.newsapp.wishlistservice.model.WishList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishlistRepository extends MongoRepository<WishList,String> {
    Optional<WishList> findByUserName(String userName);
}
