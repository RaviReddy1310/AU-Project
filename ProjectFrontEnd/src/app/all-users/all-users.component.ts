import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {

  users ?: User[] = [];
  user : User;
  constructor(private userService:UserService,
              private loginService:LoginService,
              private router:Router) {
                this.user = this.loginService.getWithExpiry('user');
                if(!this.user) {
                  this.router.navigate([`/login`]);
                }
  }

  ngOnInit() {
    this.userService.getAllUsers().subscribe((response:any) => {this.users = response});
  }

}
