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

  public tasks: Record[] = [];

  constructor(private service: MentorService){}


  ngOnInit(): void 
  {
    
  }
  
}
