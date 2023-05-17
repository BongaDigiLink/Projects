import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { Intern } from '../models/intern';


@Injectable({
  providedIn: 'root'
})

export class AuthService 
{

  constructor() { }

  private userLoggedIn: boolean = false;
  private internUser: boolean = false;
  private mentorUser: boolean = false;
  private userType: string = '';


  isLoggedIn(): boolean
  {
    console.log("User login status: "+this.userLoggedIn);
    return this.userLoggedIn;
  }

  loginUser()
  {
    this.userLoggedIn = true;
    this.setUserAccess();
  }

  logOut()
  {
    //sessionStorage.clear();
    this.userLoggedIn = false;
    console.log("User login status: "+this.userLoggedIn);

  }

  setUserAccess()
  {
    if(sessionStorage.getItem('user_modifier') === "Intern")
    {
      this.internUser = true;
      this.userType = 'Intern';
    }
    else if(sessionStorage.getItem('user_modifier') === "Mentor")
    {
      this.mentorUser = true;
      this.userType = 'Mentor';
    }
  }

  checkUserAccess(): string
  {
    return this.userType;
  }

}
