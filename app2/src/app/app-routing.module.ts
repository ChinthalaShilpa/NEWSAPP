import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginSignupComponent } from './components/login-signup/login-signup.component';
import { HomeComponent } from './components/home/home.component';
import { ArticlesComponent } from './components/articles/articles.component';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { SearchComponent } from './components/search/search.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { authGuard } from './auth.guard';


const routes: Routes = [
  {path:'',component:WelcomeComponent},
  {path:'welcome',component:WelcomeComponent},
  {path:'login',component:LoginSignupComponent},
  {path:'signup',component:LoginSignupComponent},
  {path:'home',component:HomeComponent,canActivate:[authGuard]},
  {path:'articles',component:ArticlesComponent,canActivate:[authGuard]},
  {path:'wishlist',component:WishlistComponent,canActivate:[authGuard]},
  {path:'search',component:SearchComponent,canActivate:[authGuard]},
  {path:'**',component:WelcomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
