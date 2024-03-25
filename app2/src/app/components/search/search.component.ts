import { Component } from '@angular/core';
import { NewsArticle } from 'src/app/models/NewsArticle';
import { NewsService } from 'src/app/service/news.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  topic : string =''
  newsArticles : NewsArticle[]  = [];
  errorMessage : string = ''
  messageToCheck : string  = 'Article Already Wishlisted by the User'
  errorMsgToCheck : string = ''
  topSport : NewsArticle[] = [];
  topBollyWood : NewsArticle[] = [];
  topPolitics : NewsArticle[] = [];

  constructor(private newsService : NewsService){
    //getting top sports
    newsService.getTopSports().subscribe(
      res=>{
        this.topSport = res
      }
    )

    //getting top bollywood
    newsService.getTopBollywood().subscribe(
      res=>{
        this.topBollyWood = res
      }
    )

    //getting top sports
    newsService.getTopPolitics().subscribe(
      res=>{
        this.topPolitics = res
      }
    )

  }

  getArticles(){
    this.newsService.getNewsArticles(this.topic).subscribe(
      (res)=>{
        console.log(this.topic +" articles are requested")
        this.newsArticles = res
        this.errorMessage = ''
      },
      (err)=>{
        console.log(err.error.message)
        if(err.error.message == 'Given Api key or Host are not valid..... :('){
          this.errorMessage = "You exceeded the number of requests per API Key"
        }
        else{
          this.newsArticles = []
          this.errorMessage = 'No articles found with the given topic ' + this.topic
        }
      }
    )
  }

  addArticleToWishlist(newsArticle : NewsArticle){
    // console.log("requested to wishlist",newsArticle)
    this.newsService.addArticleToWishList(newsArticle).subscribe(
      (res)=>{
        // console.log('addArticleToWishList -> ',res)
        if(res.length >= 1){
          window.alert("Article added to your whishlist")
        }
      },
      (error) => {
        console.log('addArticleToWishList -> ',error)
        console.log(error.error)
        this.errorMsgToCheck = error.error;
        if(this.errorMsgToCheck.includes(this.messageToCheck)){
          window.alert("This article already added to your wishlist")
        }
      }
    )
  }
}
