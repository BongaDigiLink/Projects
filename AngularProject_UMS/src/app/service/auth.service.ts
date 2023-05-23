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

  //private userLoggedIn: boolean = false;
  private userLoggedIn = new BehaviorSubject(false);
  private internUser: boolean = false;
  private mentorUser: boolean = false;
  private userType: string = '';
  private status: boolean=false;
  private user_id?: number | undefined;

  public toEdit(id: number | undefined)
  {
    this.user_id = id;
  }

  public getToEdit(): number | undefined
  {
    return this.user_id;
  }


  isLoggedIn(): boolean
  {
    //console.log("Emitted value: "+this.userLoggedIn.next(true));

    this.userLoggedIn.subscribe((data)=>{ this.status = data});
    return this.status;
  }

  loginUser()
  {

    this.userLoggedIn = new BehaviorSubject<boolean>(true);
    sessionStorage.setItem('active_status','ACTIVE');
    console.log((this.userLoggedIn.subscribe((data) => {
      console.log("Subscribed data: "+data);
    })));
    this.setUserAccess();
  }

  logOut()
  {
    sessionStorage.clear();
    this.userLoggedIn = new BehaviorSubject<boolean>(false);

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
      //console.log("Mentor run");
      this.mentorUser = true;
      this.userType = 'Mentor';
    }
  }

  checkUserAccess(): boolean
  {
    if(sessionStorage.getItem('active_status') === 'ACTIVE')
    {
      return true;
    }
    
    return false;
  }

  checkEditAccess(): boolean
  {
    if(sessionStorage.getItem('user_modifier') === 'Mentor')
    {
      return true;
    }

    return false;
  }

  checkCurrentUser(): boolean
  {
    console.log(this.getUserId.toString() +' '+sessionStorage.getItem('user_id')?.toString())
    if(this.getUserId.toString() === sessionStorage.getItem('user_id')?.toString())
    {
      return true;
    }

    return false;
  }

  getUserId(): number | undefined
  {
    return this.user_id;
  }

}
