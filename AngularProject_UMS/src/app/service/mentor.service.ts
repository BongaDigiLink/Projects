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

  public login(user: any): Observable<Mentor>
  {
    return this.http.post<Mentor>(`${this.apiURL}/login`, user);
  }

  /**
   * Create new mentor user to manage tasks and interns.
   * @param mentor user form inputs
   * @returns Mentor user. Store in session object.
   */
  public registerMentor(mentor: any): Observable<Mentor>
  {
    return this.http.post<Mentor>(`${this.apiURL}/registration`, mentor);
  }

   //------------------------------------Intern Management Routes

   /**
    * @returns list of all interns
    */
  public getAllInterns(): Observable<Intern[]>
  {
    return this.http.get<Intern[]>(`${this.apiURL}/all-interns`);
  }

  /**
   * @param id of intern to update
   * @param intern object to change.
   * @returns HttpResponse
   */
  public updateInternDetails(id: any, intern: any): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/edit-intern/{id}`, intern);
  }

  public getInternDetails(email: any): Observable<Intern>
  {
    return this.http.get<Intern>(`${this.apiURL}/intern/{email}`);
  }

  /**
   * 
   * @param id of intern to display details
   * @returns Intern object, details populated to the view page.
   */
  public getInternDetailsById(id: any): Observable<Intern>
  {
    return this.http.get<Intern>(`${this.apiURL}/intern-user/`+id);
  }


  public deactivateIntern(id: any): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/deactivate/`, id);
  }

  public removeIntern(id: any): Observable<any>
  {
    return this.http.delete<any>(`${this.apiURL}/delete-account/`+id)
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

  public createTask(skill: any): Observable<any>
  {
    return this.http.post<any>(`${this.apiURL}/create-task`, skill);
  }

  public updateSkill(id: any, skill: any): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/update-skill/`+id, skill);
  }

  public removeSkill(id: any): Observable<void>
  {
    return this.http.delete<any>(`${this.apiURL}/remove-skill/`+id)
  }

}
