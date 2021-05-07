import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Course } from '../course';
import { CourseService } from '../course.service';
import { LoginService } from '../login.service';
import { User } from '../user';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  user : User;
  message ?: string;
  first : boolean = false;

  addCourseForm : FormGroup;

  constructor(private loginService:LoginService,
              private router:Router,
              private courseService:CourseService) 
              {
                this.user = this.loginService.getWithExpiry('user');
                if(!this.user) {
                  this.router.navigate([`/login`]);
                }

                this.addCourseForm = new FormGroup({
                  name: new FormControl('', [
                    Validators.required, 
                    Validators.minLength(3)
                    ]),
                  description: new FormControl('', [
                    Validators.required, 
                    Validators.minLength(6)
                    ]),
                  skills: new FormControl('', [
                    Validators.required, 
                    Validators.minLength(3)
                    ]),
                });
               }

  ngOnInit(): void {
  }

  addCourse() {
    const course = new Course();
    course.name = this.addCourseForm.value.name;
    course.description = this.addCourseForm.value.description;
    course.skills = this.addCourseForm.value.skills;
    course.trainerid = this.user.id;

    this.courseService.addCourse(course).subscribe(data => {
      this.message = data,
      alert(this.message),
      this.router.navigate([`/trainer-courses`])});
  }

  get name() { return this.addCourseForm.get('name'); }

  get description() { return this.addCourseForm.get('description'); }

  get skills() { return this.addCourseForm.get('skills'); }

}
