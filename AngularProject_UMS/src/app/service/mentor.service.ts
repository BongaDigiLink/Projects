import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Mentor } from '../models/mentor';
import { Intern } from '../models/intern';
import { Skills } from '../models/skills';

@Injectable({
  providedIn: 'root'
})

export class MentorService 
{

  constructor(private http: HttpClient) { }

  private readonly apiURL = 'http://localhost:8080/mentor'

  //-------------------------------------Mentor Login (Auth)

  public login(): Observable<Mentor>
  {
    return this.http.get<Mentor>(`${this.apiURL}/login`);
  }

  public registerMentor(mentor: any): Observable<any>
  {
    return this.http.post<any>(`${this.apiURL}/registration`, mentor);
  }

   //------------------------------------Intern Management Routes

  public getAllInterns(): Observable<Intern[]>
  {
    return this.http.get<Intern[]>(`${this.apiURL}/all-interns`);
  }

  public updateInternDetails(intern: any): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/edit-intern/{id}`, intern);
  }

  public deactivateIntern(id: any, intern: any): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/deactivate/{id}`, intern);
  }

  public removeIntern(id: any): Observable<void>
  {
    return this.http.delete<any>(`${this.apiURL}/delete-account/{id}`)
  }

  //--------------------------------------Skills Management Routes.

  public getAllSkills(): Observable<Skills[]>
  {
    return this.http.get<Skills[]>(`${this.apiURL}/skills-offered`);
  }

  public addSkill(skill: any): Observable<any>
  {
    return this.http.post<any>(`${this.apiURL}/add-skill`, skill);
  }

  public updateSkill(id: any, skill: any): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/update-skill/{id}`, skill);
  }

  public removeSkill(id: any): Observable<void>
  {
    return this.http.delete<any>(`${this.apiURL}/remove-skill/{id}`)
  }

}
