import { CanActivateFn, Router } from '@angular/router';
import { NewsService } from './service/news.service';
import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router)
  var loginStatus = sessionStorage.getItem('loginStatus') 
  if(loginStatus == 'true'){
    return true
  }
  else{
    router.navigate(['login']);
    return false
  }
};
