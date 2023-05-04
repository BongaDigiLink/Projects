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

  public login(): Observable<Mentor>
  {
    return this.http.get<Mentor>(`${this.apiURL}/login`);
  }

  public registerMentor(mentor: Mentor): Observable<Mentor>
  {
    return this.http.post<Mentor>(`${this.apiURL}/registration`, mentor);
  }

  public getAllInterns(): Observable<Intern[]>
  {
    return this.http.get<Intern[]>(`${this.apiURL}/all-interns`);
  }

  public getAllSkills(): Observable<any>
  {
    return this.http.get<any>(`${this.apiURL}/skills-offered`);
  }

  public addSkill(skill: Skills): Observable<any>
  {
    return this.http.post<any>(`${this.apiURL}/add-skill`, skill);
  }

  public updateSkill(skill: Skills): Observable<Skills>
  {
    return this.http.put<Skills>(`${this.apiURL}/update-skill`, skill);
  }

  public updateInternDetails(intern: Intern): Observable<Intern>
  {
    return this.http.put<Intern>(`${this.apiURL}/edit-intern`, intern);
  }

  public removeSkill(): Observable<void>
  {
    return this.http.delete<any>(`${this.apiURL}/remove-skill`)
  }

}
