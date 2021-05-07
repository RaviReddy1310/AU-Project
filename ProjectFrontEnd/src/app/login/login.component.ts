import { Component, OnInit } from '@angular/core';
import { SocialAuthService } from 'angularx-social-login';
import { GoogleLoginProvider } from 'angularx-social-login';
import { Router } from '@angular/router'
import { LoginService } from '../login.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user:User;

  constructor( 
    private router:Router,
    private authService:SocialAuthService,
    private loginService:LoginService) { 
      this.user = this.loginService.getWithExpiry('user');
      if(!this.user) {
        this.router.navigate([`/login`]);
      } else {
        this.router.navigate([`/all-courses`]);
      }
     }

  ngOnInit(): void {
  }

  signinwithGoogle() {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(socialUser => {
      this.loginService.validateUser(socialUser.name, socialUser.email).subscribe((data:any)=> {
        this.user = data;
        this.loginService.setWithExpiry('user',this.user,60*60*1000);
        this.router.navigate([`/all-courses`]);
      });
    });

  }

  signout(){
    this.authService.signOut();
  };

}

