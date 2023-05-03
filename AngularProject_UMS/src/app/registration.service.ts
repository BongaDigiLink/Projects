import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Intern } from './models/intern';
//import { Intern } from './models/intern';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService 
{

  constructor(private registrationService:HttpClient) { }

  public registration(intern: Intern)
  {
    this.registrationService.post("http://localhost:8080/intern/register",
    intern,
    {responseType:'text' as 'json'});
  }
}
