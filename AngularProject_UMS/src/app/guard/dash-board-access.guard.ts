import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class DashBoardAccessGuard implements CanActivate 
{

  constructor(
    private authService: AuthService,
    private router: Router){}

    /**
     * check whether user is logged before granting acces to dashbaord
     * @param route navigate to home/login
     * @param state 
     * @returns boolean
     */
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree 
    {
      if(this.authService.checkUserAccess())
      {
        return true;
      }
      else
      {
        this.router.navigate(['/signin']);
        return false;
      }
  }
  
}
