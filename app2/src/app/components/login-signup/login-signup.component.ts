import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/LoginRequest';
import { User } from 'src/app/models/User';
import { NewsService } from 'src/app/service/news.service';

@Component({
  selector: 'app-login-signup',
  templateUrl: './login-signup.component.html',
  styleUrls: ['./login-signup.component.css']
})
export class LoginSignupComponent {
  constructor( private newsService : NewsService, private router : Router, ){
    // sessionStorage.removeItem('loginStatus')//this should be removed when logout button will be introduced
    // sessionStorage.removeItem('token')//this should be removed when logout button will be introduced
    sessionStorage.setItem('loginStatus','false')
  }

  loginForm: NgForm | undefined;

  sErrorMessage : string = ''
  lErrorMessage : string = ''
  sSuccessMessage : string = ''

  user : User = {
    userName : '',
    email : '',
    password : ''
  }

  userRequest : LoginRequest = {
    userName : '',
    password : ''
  }

  signUp(form : NgForm){
    console.log('user entered signup() this sholud request /signup')
    this.newsService.registerUserToNewsApp(this.user).subscribe(
      (res)=>{
        // console.log('registerUserToNewsApp -> ',res)
        window.alert(`User signup success.`)
        this.sErrorMessage = ''
        form.resetForm()
      },
      (error) => {
        if(error.error == 'Username is taken. Try using different username'){
          // window.alert(`${this.user.userName} is taken. Try using different username`)
          this.sErrorMessage = 'Username is taken. Try using different username'
        }
        else if (error.error == 'user name cannot be empty' || 
                  error.error == 'email cannot be empty' ||
                  error.error == 'password cannot be empty'
        ) {
          // window.alert('Provide proper details')
          this.sErrorMessage = 'Provide proper details'
        }
        else{
          this.sErrorMessage = 'somethings wrong with backend'
        }
        console.log('registerUserToNewsApp -> ',error)
      }
    )
  }

  login(loginForm : NgForm){
      console.log('user entered login() this sholud request /login')
      this.newsService.loginUserToNewsApp(this.userRequest).subscribe(
        (res)=>{
          sessionStorage.setItem('loginStatus','true')
          sessionStorage.setItem('token',res.response.token);
          sessionStorage.setItem('username',this.userRequest.userName);
          this.router.navigate(['/home'])
        },
        (error) => {
          console.log('registerUserToNewsApp -> ',error)
          if(error.error.response == 'User with provided creds not found'){
            // window.alert('User with provided creds not found');
            this.lErrorMessage = 'Invalid Credentials'
            loginForm.resetForm()
          }
          else{
            this.lErrorMessage = 'somethings wrong with backend'
          }
        }
      )
  }

  ngOnInit(){
  }
}
