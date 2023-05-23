import { Component, OnInit } from '@angular/core';
import { Record } from 'src/app/models/records';
import { MentorService } from 'src/app/service/mentor.service';

@Component({
  selector: 'app-record',
  templateUrl: './record.component.html',
  styleUrls: ['./record.component.css']
})
export class RecordComponent implements OnInit
{
  public internDisplay: boolean=false;
  public mentorDisplay: boolean=false;
  public tasks: Record[] = [];

  constructor(private service: MentorService){}


  ngOnInit(): void 
  {
    this.service.getCompleteTasks().subscribe((data)=>{
      //console.log(data);
      this.tasks = data;

    })

    if(sessionStorage.getItem('user_modifier') === 'Mentor')
      {
        this.mentorDisplay =true;
      }
      if(sessionStorage.getItem('user_modifier') === 'Intern')
      {
        this.internDisplay= true;
      }
  }
  
}
