import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Mentor } from '../models/mentor';
import { Intern } from '../models/intern';
import { Skills } from '../models/skills';
import { Record } from '../models/records';

@Injectable({
  providedIn: 'root'
})

export class MentorService 
{

  constructor(private http: HttpClient) { }

  private readonly apiURL = 'http://localhost:8080/mentor'

  //-------------------------------------Mentor Login (Auth)

  public login(user: Mentor): Observable<Mentor>
  {
    return this.http.post<Mentor>(`${this.apiURL}/login/`, user);
  }

  /**
   * Create new mentor user to manage tasks and interns.
   * @param mentor user form inputs
   * @returns Mentor user. Store in session object.
   */
  public registerMentor(mentor: Mentor): Observable<Mentor>
  {
    return this.http.post<Mentor>(`${this.apiURL}/registration/`, mentor);
  }

   //------------------------------------Intern Management Routes

   /**
    * @returns list of all activated interns (only active)
    */
  public getAllInterns(): Observable<Intern[]>
  {
    return this.http.get<Intern[]>(`${this.apiURL}/all-interns/`);
  }

  /**
   * @param id of intern to update
   * @param intern object to change.
   * @returns HttpResponse
   */
  public updateInternDetails(id: number, intern: any): Observable<Intern>
  {
    return this.http.put<Intern>(`${this.apiURL}/edit-intern/`+id, intern);
  }

  public getInternDetails(email: string): Observable<Intern>
  {
    return this.http.get<Intern>(`${this.apiURL}/intern/${email}`);
  }

  /**
   * @param id of intern to display details
   * @returns Intern object, details populated to the view page.
   */
  public getInternDetailsById(id: number): Observable<Intern>
  {
    return this.http.get<Intern>(`${this.apiURL}/intern-user/${id}`);
  }

  /**
   * @returns list of all interns whose accounts are deactivated.
   */
  getAllInactiveInterns(): Observable<Intern[]>
  {
    return this.http.get<Intern[]>(`${this.apiURL}/inactive-users/`);
  }

  public deactivateIntern(id: number): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/deactivate/`, id);
  }

  /**
   * @param id delete this intern account
   * @returns null
   */
  public removeIntern(id: number): Observable<any>
  {
    return this.http.delete<any>(`${this.apiURL}/delete-account/`+id)
  }

  //--------------------------------------Skills Management Routes.

  public getAllSkills(): Observable<Skills[]>
  {
    return this.http.get<Skills[]>(`${this.apiURL}/skills-offered/`);
  }

  public addSkill(skill: any): Observable<any>
  {
    return this.http.post<any>(`${this.apiURL}/add-skill/`, skill);
  }

  public createTask(skill: any): Observable<any>
  {
    return this.http.post<any>(`${this.apiURL}/create-task/`, skill);
  }

  public updateSkill(id: number, skill: Skills): Observable<any>
  {
    return this.http.put<any>(`${this.apiURL}/update-skill/`+id, skill);
  }

  public removeSkill(id: number): Observable<void>
  {
    return this.http.delete<any>(`${this.apiURL}/remove-skill/`+id)
  }

    /**
   * See all tasks
   * @returns  a list of tasks
   */
    public getTasks(): Observable<Skills[]>
    {
      return this.http.get<Skills[]>(`${this.apiURL}/all-tasks/`)
    }

    /**
     * @param id get data of a specific task
     * @returns 
     */
    public getTaskByID(id: number): Observable<Skills>
    {
      return this.http.get<Skills>(`${this.apiURL}/get-task/`+id);
    }

    public updateTask(id: number, email: string | null)
    {
      return this.http.post<any>(`${this.apiURL}/update-task/${id}`, email);
    }

    /**
     *  Get logged in users tasks (completed tasks)
     *  Admin -> Get all completed tasks records
     *  User  -> Create a record  
     */
    public getMytasks(id: number): Observable<Record[]>
    {
      return this.http.get<Record[]>(`${this.apiURL}/get-records/${id}`);
    }

    public getCompleteTasks(id: number): Observable<Record[]>
    {
      return this.http.get<Record[]>(`${this.apiURL}/user-tasks/${id}`);
    }

    public createRecord(data: any, email: string)
    {
      return this.http.post<any>(`${this.apiURL}/create-record/${email}`, data);
    }
}
