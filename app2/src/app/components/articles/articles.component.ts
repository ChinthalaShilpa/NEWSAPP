import { Component } from '@angular/core';
import { NewsArticle } from 'src/app/models/NewsArticle';
import { NewsService } from 'src/app/service/news.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent {

  articlesWishListed : NewsArticle[] = []

  topic : string = '';

  constructor(private newsService : NewsService){
    this.topic = newsService.getTopic();
    console.log('topic in article component',this.topic)
    newsService.getNewsArticles(this.topic).subscribe(
        (res)=>{
          console.log(this.topic);
          console.log('getNewsArticles -> ',res)
          this.articlesWishListed = res
        },
        (error) => {
          console.log('getNewsArticles -> ',error)
        }
      )
  }
}
