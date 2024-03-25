import { ComponentFixture, TestBed } from '@angular/core/testing';
import { WishlistComponent } from './wishlist.component';
import { NewsService } from 'src/app/service/news.service';
import { of, throwError } from 'rxjs';
import { NewsArticle } from 'src/app/models/NewsArticle';

describe('WishlistComponent', () => {
  let component: WishlistComponent;
  let fixture: ComponentFixture<WishlistComponent>;
  let newsServiceSpy: jasmine.SpyObj<NewsService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('NewsService', ['getArticlesWishListed', 'removeFromWishList']);

    TestBed.configureTestingModule({
      declarations: [WishlistComponent],
      providers: [{ provide: NewsService, useValue: spy }],
    });

    fixture = TestBed.createComponent(WishlistComponent);
    component = fixture.componentInstance;
    newsServiceSpy = TestBed.inject(NewsService) as jasmine.SpyObj<NewsService>;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should handle successful getArticlesWishListed response', () => {
    const mockArticles: NewsArticle[] = [
      {
        "Title": "Conor McGregor 'is trying to wait me out', claims Michael Chandler... as American admits frustration over delays to huge clash and targets 'dream scenario' at UFC 300",
        "Source": "dailymail.co.uk",
        "Url": "https://www.dailymail.co.uk/sport/mma/article-12826989/Conor-McGregor-trying-wait-claims-Michael-Chandler-American-admits-frustration-delays-huge-clash-targets-dream-scenario-UFC-300.html#comments",
        "PublishedOn": "2023-12-05T10:15:08.000Z",
        "Description": "Michael Chandler has admitted his frustration at the lack of movement of his planned fight with Conor McGregor.  The American has had to bide his time and be patient.",
        "Language": "en",
        "Image": "https://i.dailymail.co.uk/1s/2023/12/05/10/76454785-0-image-m-42_1701771212578.jpg",
        "SourceNationality": "gb",
        "TitleSentiment": {
          "sentiment": "negative",
          "score": -0.78
        },
        "Summary": "Michael Chandler has admitted his frustration at the lack of movement of his planned fight with Conor McGregor. I’ve seen Conor talk, completely dismiss me and act like to fight with me isn’t happening.",
        "Countries": [
          "us"
        ],
        "Categories": {
          "label": "sport",
          "IPTCCode": "medtop:15000000"
        }
      },
      {
        "Title": "Man United fan Helen Housby idolised David Beckham growing up... but now the England netball star loves inspiring the next generation herself as she gets a Sky Sports Editions cover shoot",
        "Source": "dailymail.co.uk",
        "Url": "http://www.dailymail.co.uk/sport/othersports/article-12826959/Man-United-fan-Helen-Housby-idolised-David-Beckham-England-netball-star-Sky-Sports-Editions.html",
        "PublishedOn": "2023-12-05T09:46:34.000Z",
        "Description": "England are set to take on South Africa in the three-match Vitality Netball International Series, with the first encounter taking place in Manchester on Tuesday night.",
        "Language": "en",
        "Image": "https://i.dailymail.co.uk/1s/2023/12/05/09/78587645-0-image-m-30_1701768914041.jpg",
        "SourceNationality": "gb",
        "TitleSentiment": {
          "sentiment": "positive",
          "score": 0.91
        },
        "Summary": "England netball star Helen Housby is relishing her role model status for women's sport as she became the latest athlete to get a Sky Sports Editions cover shoot. Housby, an avid Manchester United fan, grew up idolising David Beckham but now finds herself inspiring the next generation following the exponential growth in the popularity of women's sport.",
        "Countries": [
          "gb",
          "au",
          "za"
        ],
        "Categories": {
          "label": "Jai Alai (Pelota)",
          "IPTCCode": "medtop:20000968"
        }
      }
    ];
    newsServiceSpy.getArticlesWishListed.and.returnValue(of(mockArticles));

    fixture.detectChanges();

    expect(component.articlesWishListed).toEqual(mockArticles);
    expect(component.errorMessage).toBe('');
  });

  it('should handle error getArticlesWishListed response', () => {
    const errorMessage = 'no articles wishlisted';
    newsServiceSpy.getArticlesWishListed.and.returnValue(throwError({ error: { text: errorMessage } }));

    fixture.detectChanges();

    expect(component.articlesWishListed).toEqual([]);
    expect(component.errorMessage).toBe(errorMessage);
  });

  it('should remove article from wishlist successfully', () => {
    const mockArticleToRemove: NewsArticle = {
      "Title": "Man United fan Helen Housby idolised David Beckham growing up... but now the England netball star loves inspiring the next generation herself as she gets a Sky Sports Editions cover shoot",
      "Source": "dailymail.co.uk",
      "Url": "http://www.dailymail.co.uk/sport/othersports/article-12826959/Man-United-fan-Helen-Housby-idolised-David-Beckham-England-netball-star-Sky-Sports-Editions.html",
      "PublishedOn": "2023-12-05T09:46:34.000Z",
      "Description": "England are set to take on South Africa in the three-match Vitality Netball International Series, with the first encounter taking place in Manchester on Tuesday night.",
      "Language": "en",
      "Image": "https://i.dailymail.co.uk/1s/2023/12/05/09/78587645-0-image-m-30_1701768914041.jpg",
      "SourceNationality": "gb",
      "TitleSentiment": {
        "sentiment": "positive",
        "score": 0.91
      },
      "Summary": "England netball star Helen Housby is relishing her role model status for women's sport as she became the latest athlete to get a Sky Sports Editions cover shoot. Housby, an avid Manchester United fan, grew up idolising David Beckham but now finds herself inspiring the next generation following the exponential growth in the popularity of women's sport.",
      "Countries": [
        "gb",
        "au",
        "za"
      ],
      "Categories": {
        "label": "Jai Alai (Pelota)",
        "IPTCCode": "medtop:20000968"
      }
    }
    newsServiceSpy.removeFromWishList.and.returnValue(of('Article removed from wishlist.'));

    // Trigger ngOnInit manually as it's not automatically called in component creation during testing
    component.ngOnInit();

    component.removeFromWishList(mockArticleToRemove);

    expect(component.articlesWishListed).toEqual([]);
  });

  it('should handle error removing article from wishlist', () => {
    const mockArticleToRemove: NewsArticle = {
      "Title": "Man United fan Helen Housby idolised David Beckham growing up... but now the England netball star loves inspiring the next generation herself as she gets a Sky Sports Editions cover shoot",
      "Source": "dailymail.co.uk",
      "Url": "http://www.dailymail.co.uk/sport/othersports/article-12826959/Man-United-fan-Helen-Housby-idolised-David-Beckham-England-netball-star-Sky-Sports-Editions.html",
      "PublishedOn": "2023-12-05T09:46:34.000Z",
      "Description": "England are set to take on South Africa in the three-match Vitality Netball International Series, with the first encounter taking place in Manchester on Tuesday night.",
      "Language": "en",
      "Image": "https://i.dailymail.co.uk/1s/2023/12/05/09/78587645-0-image-m-30_1701768914041.jpg",
      "SourceNationality": "gb",
      "TitleSentiment": {
        "sentiment": "positive",
        "score": 0.91
      },
      "Summary": "England netball star Helen Housby is relishing her role model status for women's sport as she became the latest athlete to get a Sky Sports Editions cover shoot. Housby, an avid Manchester United fan, grew up idolising David Beckham but now finds herself inspiring the next generation following the exponential growth in the popularity of women's sport.",
      "Countries": [
        "gb",
        "au",
        "za"
      ],
      "Categories": {
        "label": "Jai Alai (Pelota)",
        "IPTCCode": "medtop:20000968"
      }
    }
    const errorMessage = 'Error removing article.';
    newsServiceSpy.removeFromWishList.and.returnValue(throwError({ error: { text: errorMessage } }));

    component.removeFromWishList(mockArticleToRemove);

    expect(component.articlesWishListed).not.toEqual([]);
    expect(component.errorMessage).toBe(errorMessage);
  });
});
