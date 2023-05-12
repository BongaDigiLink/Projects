import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MentorService } from '../service/mentor.service';
import { Intern } from '../models/intern';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit{

  public users: Intern[] = [];
  public user: Intern | undefined;

  constructor(private mentorService: MentorService,
    private router :Router)
  {}

  ngOnInit(): void {

    this.mentorService.getAllInterns()
    .subscribe((users: Intern[]) => {
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

  viewUser(id : number | undefined) {
    // this.getDetails(id);
    this.router.navigate([`/user/${id}`]).then(data => console.log("data on: "+data));
  }

  public getDetails(id: any): void{
    this.mentorService.getInternDetailsById(id)
    .subscribe(user => {
      console.log(user);
      this.user = user;
    })
  }

  public deleteUser(id: any){
    this.mentorService.removeIntern(id).subscribe( response => {
      console.log("delete response from API: "+response)
    })
  }

}
