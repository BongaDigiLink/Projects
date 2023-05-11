import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MentorService } from '../service/mentor.service';
import { Intern } from '../models/intern';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit{

  public users: Intern[] = [];
  public user: Intern | undefined;

  constructor(private mentorService: MentorService)
  {}

  ngOnInit(): void {
    this.mentorService.getAllInterns()
    .subscribe(users => {
      console.log(users);
      this.users = users;
    })
  }

  public getIntern(email: any): void{
    this.mentorService.getInternDetails(email)
    .subscribe(user => {
      console.log(user);
      this.user = user;
    })
  }


}
