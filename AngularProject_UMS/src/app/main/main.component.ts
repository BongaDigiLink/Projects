import { Component, OnInit } from '@angular/core';
import { InternService } from '../service/intern.service';
import { Router } from '@angular/router';
import { Intern } from '../models/intern';
import { MainService } from '../service/main.service';
import { Skills } from '../models/skills';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit
{
  constructor(
    private internService: InternService,
    private mainService: MainService,
    private router: Router,
  ){}

  public tasksList: Skills[] = [];

  public user_name: string | undefined ='';
  public user_type: string | undefined='';
  public internDisplay=false;
  public mentorDisplay=false;

  ngOnInit(): void 
  {
    this.mainService.getTasks().subscribe(
      (list_: Skills[]) => {
      console.log(list_),
      this.tasksList = list_;
    })

    this.getUserData();
    this.setUIView();
  }

  getUserData()
  {
    var id_user: any = sessionStorage.getItem('user_id');
    this.internService.getIntern(id_user).subscribe(
      (data: Intern) => 
    {
      this.user_type = data.role;
      this.user_name = data.name;
    })
  }

  setUIView()
  {
    if(this.user_type === 'Intern')
    {
      this.internDisplay = true;
      this.mentorDisplay = false;
    }

    if(this.user_type == 'Mentor')
    {
      this.mentorDisplay = true;
      this.internDisplay = false;
    }

  }

}
