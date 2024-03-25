import { Component } from '@angular/core';
import { NewsService } from './service/news.service';
import { User } from './models/User';
import { LoginRequest } from './models/LoginRequest';
import { NewsArticle } from './models/NewsArticle';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'NewsApp';

  user : User = {
    userName : "ShilpaChinthala",
    email : "Shilpa@gmail.com",
    password : "shilpa@123"
  }

  loginRequest : LoginRequest = {
    userName : "ShilpaChinthala",
    password : "shilpa@123"
  }

  article : NewsArticle = {
    Title: "Paul Scholes SAVAGES 'lazy' and 'rubbish' Man United...",
    Source: "dailymail.co.uk",
    Url: "http://www.dailymail.co.uk/sport/football/article-12819691/Paul-Scholes-SAVAGES-lazy-rubbish-Man-United-defeat-Newcastle-Erik-ten-Hags.html",
    PublishedOn: "2023-12-03T09:13:19.000+00:00",
    Description: "Scholes was hugely disappointed with United's display...",
    Language: "en",
    Image: "https://i.dailymail.co.uk/1s/2023/12/03/09/78519015-0-image-m-33_1701594556011.jpg",
    SourceNationality: "gb",
    TitleSentiment: {
      sentiment: "negative",
      score: -0.99,
    },
    Summary: "Manchester United legend Paul Scholes has launched a stinging criticism...",
    Countries: [],
    Categories: {
      label: "sport",
      IPTCCode: "medtop:15000000",
    },
  } 

  constructor(private newsService : NewsService){
    // newsService.registerUserToNewsApp(this.user);
    // newsService.loginUserToNewsApp(this.loginRequest);
    // newsService.getNewsArticles('sports');
    // newsService.addArticleToWishList(this.article);
    // newsService.getArticlesWishListed();
  }
}
