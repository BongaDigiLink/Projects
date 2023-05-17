import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit 
{
  constructor(
    private authService: AuthService,
    private router: Router){}

    public btnLogin: boolean=false;
    public btnLogOut: boolean=false;


  ngOnInit(): void 
  {
    if(this.authService.isLoggedIn())
    {
      this.btnLogin=false;
      this.btnLogOut=true;
    }

  }

  logInUser()
  {
    this.btnLogOut = true;
    this.btnLogin = false;
    this.router.navigate(['/dashboard']);
  }

  logOutUser()
  {
    this.authService.logOut();
    this.router.navigate(['/signin']);
  }

}
