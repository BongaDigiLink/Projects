import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MentorService } from '../../service/mentor.service';
import { Intern } from '../../models/intern';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit
{

  public users: Intern[] = [];
  public ausers: Intern[] = [];
  public user: Intern | undefined;
  public mentorDisplay="";

  constructor(private mentorService: MentorService,
    private authService: AuthService,
    private router :Router)
  {}

  ngOnInit(): void 
  {
    this.getUsers();
    this.getInactiveUsers();

    if(sessionStorage.getItem('user_modifier') === "Mentor")
    {
      this.mentorDisplay = "true";
    }
  }

  public getIntern(email: string): void
  {
    this.mentorService.getInternDetails(email)
    .subscribe(user => {
      console.log(user);
      this.user = user;
    })
  }

  viewUser(id : number | undefined) 
  {
    //this.authService.checkEditAccess();
    this.authService.toEdit(id);
    this.router.navigate([`/user/${id}`]);//.then(data => console.log("data on: "+data));
  }

  public getDetails(id: number): void
  {
    this.mentorService.getInternDetailsById(id)
    .subscribe(user => {
      console.log(user);
      this.user = user;
    })
  }

  getUsers()
  {
    this.mentorService.getAllInterns()
    .subscribe((users: Intern[]) => {
      this.users = users;
    })
  }

  getInactiveUsers()
  {
    this.mentorService.getAllInactiveInterns()
    .subscribe((data: Intern[]) => {
      console.log(data);
      this.ausers = data;
    })
  }

  public deleteUser(id: any)
  {
    this.mentorService.removeIntern(id).subscribe( response => {
      console.log("delete response from API: "+response)
    }),
    this.router.navigate(['/dashboard']);
  }

}
