import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Skills } from '../models/skills';

@Injectable({
  providedIn: 'root'
})
export class MainService 
{

  constructor(private http: HttpClient) { }

  private readonly apiUrl = "http://localhost:8080/intern";

  /**
   * @returns due tasks to display on the front page.
   */
  public getTasks(): Observable<Skills[]>
  {
    return this.http.get<Skills[]>(`${this.apiUrl}/tasks/`);
  }
}
