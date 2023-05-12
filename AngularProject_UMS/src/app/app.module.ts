import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
//Added
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { HomeComponent } from './home/home.component';
import { UserViewComponent } from './user-view/user-view.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { MainComponent } from './main/main.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateTaskComponent } from './create-task/create-task.component';

@NgModule(
  {
  declarations: 
  [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    UserDetailsComponent,
    HomeComponent,
    UserViewComponent,
    NavbarComponent,
    FooterComponent,
    MainComponent,
    CreateTaskComponent
  ],
  imports: 
  [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {path: '', redirectTo : 'home', pathMatch: 'full'},
      {path: 'home', component: HomeComponent},
      {path: 'signup', component: SignUpComponent},
      {path: 'signin', component: SignInComponent},
      {path: 'dashboard', component: MainComponent},
      {path: 'user-details', component: UserDetailsComponent},
      {path: 'user/:id', component: UserViewComponent},
      {path: 'create-task', component: CreateTaskComponent},
      
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
