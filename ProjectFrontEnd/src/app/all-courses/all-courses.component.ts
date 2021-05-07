import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../course';
import { CourseService } from '../course.service';
import { LoginService } from '../login.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.css']
})
export class AllCoursesComponent implements OnInit {

  courses : Course[] = [];
  user : User;
  message : string = "";
  search : string;
  searchCourses ?: Course[];
  all : boolean;
  csearch : boolean;

  constructor(private courseService:CourseService,
              private loginService:LoginService,
              private router:Router,
              private userService:UserService) {
                this.user = this.loginService.getWithExpiry('user');
                if(!this.user) {
                  this.router.navigate([`/login`]);
                }

                this.all = true;
                this.csearch = false;
  }

  ngOnInit(): void {
    this.courseService.getAllCourses().subscribe((response:any) => {this.courses = response});
  }

  addCourseToStudy(id:number) {
    this.userService.addCourseToStudy(id,this.user.id).subscribe((response:any) => {
      this.message = response,
      alert(this.message),
      this.router.navigate([`/my-courses`])});
  }

  searchByname(term:string) {
    this.courseService.searchByname(term).subscribe((response:any) => {
      this.searchCourses = response;
      if(this.searchCourses === null) {
        alert("No such courses available");
        this.search = "";
      } else {
        this.all = false;
        this.csearch = true;
      }
    })
  }

  viewall() {
    this.all = true;
    this.csearch = false;
    this.search = "";
  }

}
