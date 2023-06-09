import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable({
  providedIn: 'root'
})

export class UserEditGuard implements CanActivate
 {

  constructor(
    private authService: AuthService,
    private router: Router){}

    /**
     * Check whether is of type Mentor for editing
     * @param route redirect / display unauthorized access for this user type.
     * @param state 
     * @returns true if Mentor, else return false
     */
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree 
    {
      if(this.authService.getToEdit()?.toString() === sessionStorage.getItem('user_id'))
      {
        return true;
      }

      if(this.authService.checkEditAccess())
      {
        return true;
      }
      else
      {
        this.router.navigate(['/dashboard']);
        return false;
      }
  }
  
}
