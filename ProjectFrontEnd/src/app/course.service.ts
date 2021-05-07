import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Course } from './course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http:HttpClient) { }

  addCourse(course:Course) {
    return this.http.post('http://localhost:8080/app/course/add-course',course, {responseType : 'text'});
  }
  
  public getAllCourses(){
    return this.http.get('http://localhost:8080/app/course/all-courses');
  }

  public getCourseById(id:number) {
    return this.http.get("http://localhost:8080/app/course/get-course/"+id, {responseType : 'json'});
  }

  public updateCourse(course:Course) {
    return this.http.post('http://localhost:8080/app/course/update-course',course, {responseType : 'text'});
  }

  public searchByname(term: string) {
    return this.http.get('http://localhost:8080/app/course/search-course/'+term);
  }

  public getCourseNames() {
    return this.http.get('http://localhost:8080/app/course/name-courses');
  }

  public getUserdata() {
    return this.http.get('http://localhost:8080/app/course/user-data');
  }
}
