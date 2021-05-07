import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient,
              private router:Router) { }

  public setWithExpiry(key:string, value:User, ttl:number) {
    const now = new Date();

    const item = {
      value: value,
      expiry: now.getTime() + ttl,
    }
    localStorage.setItem(key, JSON.stringify(item));
  }

  public validateUser(name:String, email:String) {
    return this.http.get('http://localhost:8080/app/user/validate-user/'+name+'&'+email);
  }

  getWithExpiry(key:string) {
    const itemStr = localStorage.getItem(key);
    
    if (!itemStr) {
      return null;
    }
    const item = JSON.parse(itemStr);
    const now = new Date();
    
    if (now.getTime() > item.expiry) {
      localStorage.removeItem(key);
      return null;
    }
    return item.value;
  }

  logout() {
    localStorage.removeItem('user');
    this.router.navigate([`/login`]);
  }

}
