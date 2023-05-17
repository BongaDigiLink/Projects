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

    public btnLogin: boolean=true;
    public btnLogOut: boolean=false;


  ngOnInit(): void 
  {
    if(this.authService.checkUserAccess())
    {
      this.btnLogin=false;
      this.btnLogOut=true;
    }

  }

  logInUser()
  {
    this.btnLogOut = true;
    this.btnLogin = false;
    this.router.navigate(['/signin']);
  }

  logOutUser()
  {
    this.authService.logOut();
    this.btnLogin = true;
    this.btnLogOut=false;
    this.router.navigate(['/home']);
  }

}
