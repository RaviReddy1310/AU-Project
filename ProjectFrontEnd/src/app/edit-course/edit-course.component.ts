import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from '../course';
import { CourseService } from '../course.service';
import { LoginService } from '../login.service';
import { User } from '../user';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.css']
})
export class EditCourseComponent implements OnInit {

  course : Course;
  user: User;
  message :string;
  id1:number;

  addCourseForm : FormGroup;

  constructor(private activatedroute:ActivatedRoute,
              private courseService:CourseService,
              private loginService:LoginService,
              private router:Router) { 
                this.user = this.loginService.getWithExpiry('user');
                if(!this.user) {
                  this.router.navigate([`/login`]);
                }

                 this.id1 = Number(this.activatedroute.snapshot.paramMap.get('id'));
                 this.courseService.getCourseById(this.id1).subscribe((response:any) => {
                   this.course = response,
                   this.addCourseForm = new FormGroup({
                    name: new FormControl(this.course.name, [
                      Validators.required, 
                      Validators.minLength(3)
                      ]),
                    description: new FormControl(this.course.description, [
                      Validators.required, 
                      Validators.minLength(6)
                      ]),
                    skills: new FormControl(this.course.skills, [
                      Validators.required, 
                      Validators.minLength(3)
                      ]),
                  });
                });
              }

  ngOnInit(): void {
  }

  updateCourse() {

    this.course.name = this.addCourseForm.value.name;
    this.course.description = this.addCourseForm.value.description;
    this.course.skills = this.addCourseForm.value.skills;

    this.courseService.updateCourse(this.course).subscribe((response:any) => {
      this.message = response,
      alert(this.message),
      this.router.navigate([`/trainer-courses`])
    });
  }

  get name() { return this.addCourseForm.get('name'); }

  get description() { return this.addCourseForm.get('description'); }

  get skills() { return this.addCourseForm.get('skills'); }

}
