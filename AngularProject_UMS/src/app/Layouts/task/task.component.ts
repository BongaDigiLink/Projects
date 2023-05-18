import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MentorService } from 'src/app/service/mentor.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit
{

  constructor(private dataService: MentorService,
    private router_a: ActivatedRoute){}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  getTask()
  {
    
  }
  

}
