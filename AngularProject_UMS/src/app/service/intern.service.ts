import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Intern } from '../models/intern';
import { Skills } from '../models/skills';

@Injectable({
  providedIn: 'root'
})
export class InternService 
{
  constructor(private http: HttpClient) { }


  private apiURL = 'http://localhost:8080/intern'

    /**
     * Credentials
   * Send email/username and passworod
   * @returns user object(Intern) and true, when account exists and credentials match.
   */
    public loginIntern(intern: any): Observable<Intern>
    {
      return this.http.post<Intern>(`${this.apiURL}/login/`, intern);
    }
  
    /**
     * Capture details and submit to api->db.
     * @param intern 
     * @returns true, account created. user redirected to login page.
     */
    public register(intern: any): Observable<any>
    {
      return this.http.post(`${this.apiURL}/register/`,intern);
    }


  //------------------------------------Intern PUT update routes
  /**
   * Update user account details
   * @param intern 
   * @returns true when updated.
   */
  public updateIntern(id: any, intern: any): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/update/{id}`, intern);
  }

  /**
   * 
   * @param intern 
   * @returns true when account deactivated.
   */
  public deactivateIntern(id: any, intern: any): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/deactivate/{id}`, intern);
  }

  /**
   * 
   * @param id 
   * @returns current logged in user object.
   */
  public getIntern(id: number): Observable<Intern>
  {
    return this.http.get<Intern>(`${this.apiURL}/intern-user/${id}`);
  }


  //------------------------------------Intern skills routes
  /**
   * For select statement. See list of all possible course to
   * register from. Used in Sign Up
   * @returns 
   */
  public getSkills(): Observable<Skills[]>
  {
    return this.http.get<Skills[]>(`${this.apiURL}/skills/`);
  }

  /**
   * See all tasks from this [skills programme] programee
   * @param id of a skill to query [01 - Data, 24 -AI, ...]
   * @returns  a list of tasks from a specific training programme
   */
  public getSkillsTasks(name: any): Observable<Skills[]>
  {
    return this.http.get<Skills[]>(`${this.apiURL}/skills-tasks/${name}`)
  }


}
