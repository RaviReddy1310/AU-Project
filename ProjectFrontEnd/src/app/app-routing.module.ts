import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCourseComponent } from './add-course/add-course.component';
import { AddFilesComponent } from './add-files/add-files.component';
import { AllCoursesComponent } from './all-courses/all-courses.component';
import { AllUsersComponent } from './all-users/all-users.component';
import { AllVersionsComponent } from './all-versions/all-versions.component';
import { EditCourseComponent } from './edit-course/edit-course.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MyCoursesComponent } from './my-courses/my-courses.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { PieChartComponent } from './pie-chart/pie-chart.component';
import { TrainerCoursesComponent } from './trainer-courses/trainer-courses.component';
import { ViewStudentsComponent } from './view-students/view-students.component';

const routes: Routes = [
  {path : 'login', component : LoginComponent},
  {path : '', redirectTo : '/login', pathMatch : 'full'},
  {path : 'home', component : HomeComponent},
  {path : 'add-course', component : AddCourseComponent},
  {path : 'all-courses', component : AllCoursesComponent},
  {path : 'all-users', component : AllUsersComponent},
  {path : 'my-courses', component : MyCoursesComponent},
  {path : 'trainer-courses', component : TrainerCoursesComponent},
  {path : 'edit/:id', component : EditCourseComponent},
  {path : 'view-students/:id', component : ViewStudentsComponent},
  {path : 'add-file/:id', component : AddFilesComponent},
  {path : 'all-versions/:id', component : AllVersionsComponent},
  {path : 'trends', component : PieChartComponent},
  {path : '**', component : NotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    paramsInheritanceStrategy: "always"
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
