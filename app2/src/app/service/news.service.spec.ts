import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { NewsService } from './news.service';
import { NewsArticle } from '../models/NewsArticle';
import { User } from '../models/User';
import { LoginRequest } from '../models/LoginRequest';
import { LoginResponse } from '../models/LoginResponse';

describe('NewsService', () => {
  let service: NewsService;
  let httpMock: HttpTestingController;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [NewsService],
    });
    service = TestBed.inject(NewsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should set and get the topic', () => {
    const topic = 'Technology';
    service.setTopic(topic);
    expect(service.getTopic()).toEqual(topic);
  });

  it('should return login status', () => {
    sessionStorage.setItem('loginStatus', 'false');
    expect(service.getLoginStatus()).toBeTruthy();
    sessionStorage.setItem('loginStatus', 'true');
    expect(service.getLoginStatus()).toBeFalsy();
  });

  it('should login user to News App', () => {
    const loginRequest: LoginRequest = { userName: 'testuser', password: 'testpassword' };
    const mockLoginResponse : LoginResponse = { 
      response : {
        message : 'message',
        token : 'token'
      }
    }  

    service.loginUserToNewsApp(loginRequest).subscribe((response) => {
      expect(response).toEqual(mockLoginResponse);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/v1.0/auth/login');
    expect(req.request.method).toBe('POST');
    req.flush(mockLoginResponse);
  });

  it('should register user to News App', () => {
    const user: User = { userName: 'testuser', email: 'testuser@gmail.com', password: 'testpassword' };

    service.registerUserToNewsApp(user).subscribe();

    const req = httpMock.expectOne('http://localhost:8081/api/v1.0/user/register');
    expect(req.request.method).toBe('POST');
    req.flush({});
  });

});
