import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import { Intern } from './models/intern';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService 
{

  constructor(private registrationService:HttpClient) { }

  public registration(intern: any)
  {
    this.registrationService.post("http://localhost:8080/signup",
    intern,
    {responseType:'text' as 'json'});
  }
}
