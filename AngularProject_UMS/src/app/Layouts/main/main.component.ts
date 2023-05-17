import { Component, OnInit } from '@angular/core';
import { InternService } from '../../service/intern.service';
import { Router } from '@angular/router';
import { Intern } from '../../models/intern';
import { MainService } from '../../service/main.service';
import { Skills } from '../../models/skills';

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

      //console.log("Comparison: "+this.user_type === `${data.role}`);

      if(data.role === 'Mentor')
      {
        this.mentorDisplay =true;;
      }
      if(data.role === 'Intern')
      {
        this.internDisplay= true;
      }
    })
  }

}
