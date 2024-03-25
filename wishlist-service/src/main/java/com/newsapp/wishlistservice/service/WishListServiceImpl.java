package com.newsapp.wishlistservice.service;

import com.newsapp.wishlistservice.exception.ArticleAlreadyWishlisted;
import com.newsapp.wishlistservice.exception.NoArticlesWishlisted;
import com.newsapp.wishlistservice.model.NewsArticle;
import com.newsapp.wishlistservice.model.WishList;
import com.newsapp.wishlistservice.repository.WishlistRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
public class WishListServiceImpl implements WishListService{

    @Autowired
    private AuthenticationServerFeignClient feignClient;

    @Autowired
    private WishlistRepository wishlistRepository;

    public WishList addArticleToWishList(String userName, NewsArticle article) throws ArticleAlreadyWishlisted {

        Optional<WishList> wishLists = wishlistRepository.findByUserName(userName);
        if(wishLists.isPresent()){
            for(NewsArticle article1 : wishLists.get().getArticles()){
                if(article.getTitle().equals(article1.getTitle())){
                    throw new ArticleAlreadyWishlisted("Article Already Wishlisted by the User " + userName );
                }
            }
            wishLists.get().getArticles().add(article);
            return wishlistRepository.save(wishLists.get());
        }
        else{
            WishList newWishList = new WishList();
            newWishList.setUserName(userName);
            newWishList.setArticles(List.of(article));
            return wishlistRepository.save(newWishList);
        }
    }

    public List<NewsArticle> getWishListedArticles(String userName) throws NoArticlesWishlisted {
        Optional<WishList> wishLists = wishlistRepository.findByUserName(userName);
        if(wishLists.isPresent()){
            return wishLists.get().getArticles();
        }
        else {
            throw new NoArticlesWishlisted("No Articles are Wishlisted yet by the user : "+ userName);
        }
    }

    @Override
    public WishList deleteAnArticleFromWishlist(NewsArticle newsArticle,String userName) throws NoArticlesWishlisted {
        Optional<WishList> wishList = wishlistRepository.findByUserName(userName);
        log.info(wishList.toString());
        if(wishList.get().getArticles().contains(newsArticle)){
            log.info("wishlist found");
            wishList.get().getArticles().remove(newsArticle);
            log.info("wishlist changed");
            return wishlistRepository.save(wishList.get());
        }
        else{
            log.info("error while removing");
            throw new NoArticlesWishlisted("Article not present");
        }
    }
}
