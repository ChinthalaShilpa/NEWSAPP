import { Component } from '@angular/core';
import { NewsArticle } from 'src/app/models/NewsArticle';
import { NewsService } from 'src/app/service/news.service';
import {MatExpansionModule} from '@angular/material/expansion';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent {

  errorMessage : string = ''

    articlesWishListed : NewsArticle[] = []
    constructor(private newsService : NewsService){
      newsService.getArticlesWishListed().subscribe(
        (res)=>{
          this.articlesWishListed = res
          // console.log(this.articlesWishListed)
          this.errorMessage = ''
        },
        (error)=>{
          this.errorMessage = 'no articles wishlisted'
          this.articlesWishListed = []
          console.log("no articles wishlisted")
        }
      )
    }

    removeFromWishList(newsArticle : NewsArticle){
      this.newsService.removeFromWishList(newsArticle).subscribe(
        (res)=>{
          console.log("remove article success -> ",res)
        },
        (err)=>{
          console.log(err.error.text)
          if(err.error.text === 'Article removed from wishlist.'){
            // window.alert('Article removed from wishlist.')
            this.articlesWishListed = this.articlesWishListed.filter(
              article => article.Title !== newsArticle.Title
            )
          }
          console.log("remove article error -> ",err.error.status)
        }
      )
    }
}
