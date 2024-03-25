import { ChangeDetectorRef, Component, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { NewsService } from 'src/app/service/news.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit,OnDestroy{

  topic : string = ''
  username : string | null = ''
  private routeSubscription !: Subscription;

  constructor(private newsService : NewsService, private router : Router,private activatedRoute: ActivatedRoute,private cdr: ChangeDetectorRef){
    
  }

  getLoginStatus(){
    return this.newsService.getLoginStatus();
  }

  logout(){
    sessionStorage.removeItem('loginStatus')
    sessionStorage.removeItem('token')
    sessionStorage.removeItem('username')
    this.router.navigate([''])
  }

  setNewsTopic(){
    this.newsService.setTopic(this.topic)
    this.topic = ''
    this.router.navigate(['/articles'])
  }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('username');
    console.log(this.username);

    // Subscribe to the NavigationEnd event
    this.routeSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Navigation has completed, perform actions here
        this.handleNavigation();
      }
    });
  }

  ngOnDestroy(): void {
    // Unsubscribe from the route events to avoid memory leaks
    this.routeSubscription.unsubscribe();
  }

  // Other methods...

  private handleNavigation() {
    // Code to handle actions on navigation
    // For example, re-fetch user data or update the component state
    this.username = sessionStorage.getItem('username');
    this.cdr.detectChanges(); // If necessary
  }
}
