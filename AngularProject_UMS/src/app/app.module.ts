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
    FooterComponent
  ],
  imports: 
  [
    BrowserModule,
    RouterModule.forRoot([
      {path: '', redirectTo : 'home', pathMatch: 'full'},
      {path: 'signin', component: SignInComponent},
      {path: 'signup', component: SignUpComponent},
      {path: 'user-details', component: UserDetailsComponent},
      {path: 'home', component: HomeComponent},
      {path: 'user', component: UserViewComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
