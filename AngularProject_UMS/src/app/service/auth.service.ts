import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { Intern } from '../models/intern';

interface SigninCredentials{
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService 
{

  constructor(private http: HttpClient) { }

  signedin$ = new BehaviorSubject(false);
  baseUrl = 'http://localhost:8080';

  signin(credentials: any) {
    return this.http.post(`${this.baseUrl}/intern/login/`, credentials).pipe(
      tap((res: Intern) => {
        sessionStorage.setItem('user', `${res.email}`);
        sessionStorage.setItem('id', `${res.id}`);
        this.signedin$.next(true);
      })
    )
  }

}
