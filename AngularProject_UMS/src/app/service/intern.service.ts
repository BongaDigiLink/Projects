import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Intern } from '../models/intern';

@Injectable({
  providedIn: 'root'
})
export class InternService 
{
  constructor(private http: HttpClient) { }

  private apiURL = 'http://localhost:8080/intern'

  /**
   * 
   * @returns 
   */
  public getSkills(): Observable<any>
  {
    return this.http.get<any>(`${this.apiURL}/skills/`);
  }

  /**
   * 
   * @returns 
   */
  public loginIntern(intern: any): Observable<any>
  {
    return this.http.get<Intern>(`${this.apiURL}/login/`)
  }

  /**
   * 
   * @param intern 
   * @returns 
   */
  public register(intern: any): Observable<any>
  {
    return this.http.post(`${this.apiURL}/register/`,intern);
  }

  /**
   * 
   * @param intern 
   * @returns 
   */
  public updateIntern(intern: Intern): Observable<Intern>
  {
    return this.http.put<Intern>(`${this.apiURL}/update/`, intern);
  }

  /**
   * 
   * @param intern 
   * @returns 
   */
  public deactivateIntern(intern: Intern): Observable<Intern>
  {
    return this.http.put<Intern>(`${this.apiURL}/deactivate/`, intern);
  }


}
