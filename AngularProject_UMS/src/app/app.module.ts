import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
//Added
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { SignInComponent } from './Layouts/sign-in/sign-in.component';
import { SignUpComponent } from './Layouts/sign-up/sign-up.component';
import { UserDetailsComponent } from './Layouts/user-details/user-details.component';
import { HomeComponent } from './Layouts/home/home.component';
import { UserViewComponent } from './Layouts/user-view/user-view.component';
import { NavbarComponent } from './Layouts/navbar/navbar.component';
import { FooterComponent } from './Layouts/footer/footer.component';
import { MainComponent } from './Layouts/main/main.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateTaskComponent } from './Layouts/create-task/create-task.component';
import { DashBoardAccessGuard } from './guard/dash-board-access.guard';

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
      {path: 'dashboard', component: MainComponent, canActivate:[DashBoardAccessGuard]},
      {path: 'user-details', component: UserDetailsComponent},
      {path: 'user/:id', component: UserViewComponent},
      {path: 'create-task', component: CreateTaskComponent},

    ])
  ],
  providers: [DashBoardAccessGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
