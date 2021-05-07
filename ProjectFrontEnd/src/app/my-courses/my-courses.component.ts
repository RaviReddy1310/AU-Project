import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../course';
import { FileService } from '../file.service';
import { LoginService } from '../login.service';
import { TrainingMaterials } from '../training-materials';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css']
})
export class MyCoursesComponent implements OnInit {

  user : User;
  courses ?: Course[];
  message : String;
  material : TrainingMaterials;
  
  constructor(private loginService:LoginService,
              private fileService:FileService,
              private router:Router,
              private userService:UserService) { 
                this.user = this.loginService.getWithExpiry('user');
                if(!this.user) {
                  this.router.navigate([`/login`]);
                }
  }

  ngOnInit(): void {
    this.userService.myCourses(this.user.id).subscribe((response:any) => {
      this.courses = response;
      if(this.courses === null) {
        alert("You are not enrolled in any courses. First Enroll.");
        this.router.navigate([`all-courses`]);
      }
    });
  }

  dropCourse(courseid:number) {
    this.userService.dropCourse(courseid, this.user.id).subscribe((response:any) => {
      this.message = response,
      alert(this.message),
      window.location.reload();
    });
  }

  downloadContent(id:number) {
    this.fileService.getMaterialByCId(id).subscribe((response:any) => {
      this.material = response;
      if (!this.material) {
        alert("No material to download");
      } else {
        this.fileService.downloadMaterial(this.material.filedata, this.material.filetype);
      }
    });
  }

}
