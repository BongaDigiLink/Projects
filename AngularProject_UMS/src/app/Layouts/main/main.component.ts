import { Component, OnInit } from '@angular/core';
import { InternService } from '../../service/intern.service';
import { Router } from '@angular/router';
import { Intern } from '../../models/intern';
import { MainService } from '../../service/main.service';
import { Skills } from '../../models/skills';
import { AuthService } from 'src/app/service/auth.service';

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
    private authService: AuthService
  ){}

  public tasksList: Skills[] = [];

  public user_name: string | undefined ='';
  public user_type="";
  public internDisplay: boolean=false;
  public mentorDisplay: boolean=false;

  ngOnInit(): void
  {
    this.mainService.getTasks().subscribe(
      (list_: Skills[]) => {
      console.log(list_),
      this.tasksList = list_;
    })

    this.getUserData();

  }

  getUserData()
  {
    var id_user: any = sessionStorage.getItem('user_id');
    this.internService.getIntern(id_user).subscribe(
      (data: Intern) =>
    {
      this.user_name = data.name;
      sessionStorage.setItem('user_modifier',`${data.role}`);

      if(sessionStorage.getItem('user_modifier') === 'Mentor')
      {
        this.mentorDisplay =true;
      }
      if(sessionStorage.getItem('user_modifier') === 'Intern')
      {
        this.internDisplay= true;
      }
    })
  }

}
