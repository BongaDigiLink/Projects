import { Component, OnInit } from '@angular/core';
import { InternService } from '../service/intern.service';
import { Router } from '@angular/router';
import { Intern } from '../models/intern';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit
{
  constructor(
    private internService: InternService,
    private router: Router,
  ){}

  public user_name: string | undefined ='';
  user_type: string | undefined='';
  


  ngOnInit(): void 
  {
    this.displayTasks();
    this.getUserData();
  }

  displayTasks()
  {
    console.log('User is now on dashboard page.');
  }

  getUserData()
  {
    var id_user: any = localStorage.getItem('user_id');
    this.internService.getIntern(id_user).subscribe(
      (data: Intern) => 
    {
      this.user_type = data.role;
      this.user_name = data.name;
    })
  }

}
