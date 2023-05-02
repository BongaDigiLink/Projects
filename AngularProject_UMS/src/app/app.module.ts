import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
//Added
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { HomeComponent } from './home/home.component';

@NgModule(
  {
  declarations: 
  [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    UserDetailsComponent,
    HomeComponent
  ],
  imports: 
  [
    BrowserModule,
    RouterModule.forRoot([
      {path: '', redirectTo : 'home', pathMatch: 'full'},
      {path: 'signin', component: SignInComponent},
      {path: 'signup', component: SignUpComponent},
      {path: 'user-details', component: UserDetailsComponent},
      {path: 'home', component: HomeComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
