import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { SocialAuthServiceConfig } from 'angularx-social-login';
import { SocialLoginModule, GoogleLoginProvider } from 'angularx-social-login';
import { AllUsersComponent } from './all-users/all-users.component';
import { AllCoursesComponent } from './all-courses/all-courses.component';
import { AddCourseComponent } from './add-course/add-course.component';
import { MyCoursesComponent } from './my-courses/my-courses.component';
import { TrainerCoursesComponent } from './trainer-courses/trainer-courses.component';
import { EditCourseComponent } from './edit-course/edit-course.component';
import { ViewStudentsComponent } from './view-students/view-students.component';
import { AddFilesComponent } from './add-files/add-files.component';
import { AllVersionsComponent } from './all-versions/all-versions.component';
import { PieChartComponent } from './pie-chart/pie-chart.component'
import { ChartsModule } from 'ng2-charts'

@NgModule({
  declarations: [
    AppComponent,
    NotfoundComponent,
    LoginComponent,
    HomeComponent,
    AllUsersComponent,
    AllCoursesComponent,
    AddCourseComponent,
    MyCoursesComponent,
    TrainerCoursesComponent,
    EditCourseComponent,
    ViewStudentsComponent,
    AddFilesComponent,
    AllVersionsComponent,
    PieChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    SocialLoginModule,
    ChartsModule
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '669840190111-of88ntd0sgnho6u46avdsvr5rjn7ldr8.apps.googleusercontent.com'
            )
          }
        ]
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
