import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { LoginRequest } from '../models/LoginRequest';
import { NewsArticle } from '../models/NewsArticle';
import { LoginResponse } from '../models/LoginResponse';
import { BehaviorSubject, Observable } from 'rxjs';
import { ApiResponse } from '../models/ApiResponse';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  
  
  token : string  = '' 
  topic : string = '';  
  
  constructor(private httpClient : HttpClient) { 
  }

  AUTH_URL : string = 'http://localhost:8080/api/v1.0';
  PROFILE_URL : string = 'http://newapp-lb-1016212984.us-east-1.elb.amazonaws.com:8082/api/v1.0';
  NEWS_PROVIDER_URL : string = 'http://localhost:8082/api/v1.0';
  WISHLIST_URL : string = 'http://localhost:8083/api/v1.0';

  testToken : string = 'Bearer '

  setTopic(topic: string) {
    this.topic = topic;
  }

  getTopic() {
    return this.topic;
  }

  getLoginStatus(){
    if(sessionStorage.getItem('loginStatus') == 'false'){
      return true;
    }
    else{
      return false;
    }
  }

  loginUserToNewsApp(loginRequest : LoginRequest) : Observable<LoginResponse>{
    return this.httpClient.post<LoginResponse>(this.AUTH_URL+"/auth/login",loginRequest);
  }

  registerUserToNewsApp(user : User){
    return this.httpClient.post(this.PROFILE_URL+"/user/register",user)
  }

  getNewsArticles(topic : string) : Observable<NewsArticle[]>{
    return this.httpClient.get<NewsArticle[]>(this.NEWS_PROVIDER_URL+"/news/search/"+topic, { headers: { Authorization: 'Bearer ' + sessionStorage.getItem('token') } });
    // return this.httpClient.get<NewsArticle[]>('http://localhost:3000/news');//for testing
  }

  addArticleToWishList(article : NewsArticle) : Observable<NewsArticle[]> {
    // this.token = this.token + sessionStorage.getItem('token');
    return this.httpClient.post<NewsArticle[]>(this.WISHLIST_URL+"/wishlist/add", article , { headers: { Authorization: 'Bearer ' + sessionStorage.getItem('token') } })
  }

  getArticlesWishListed(): Observable<NewsArticle[]>{
    return this.httpClient.get<NewsArticle[]>(this.WISHLIST_URL+"/wishlist/get" , { headers: { Authorization: 'Bearer ' + sessionStorage.getItem('token') } });
    // return this.httpClient.get<NewsArticle[]>('http://localhost:3000/news');//for testing
  }

  removeFromWishList(newsArticle: NewsArticle) : Observable<string>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      }),
      body: newsArticle 
    };
    return this.httpClient.delete<string>(this.WISHLIST_URL+"/wishlist/remove" , httpOptions)
  }

  getTopSports() : Observable<NewsArticle[]> {
    return this.httpClient.get<NewsArticle[]>('http://localhost:3000/sports');
  }

  getTopBollywood() : Observable<NewsArticle[]> {
    return this.httpClient.get<NewsArticle[]>('http://localhost:3000/bollywood');
  }

  getTopPolitics() : Observable<NewsArticle[]> {
    return this.httpClient.get<NewsArticle[]>('http://localhost:3000/politics');
  }

  public isAuthenticated(): boolean {
    return (
      !!sessionStorage.getItem('token') || !!sessionStorage.getItem('loginStatus')
    );
  }
}
