import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Skills } from 'src/app/models/skills';
import { MentorService } from 'src/app/service/mentor.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit
{
  public task_id!:Number;
  public task_!: Skills;

  constructor(private dataService: MentorService,
    private router: ActivatedRoute){}

  ngOnInit(): void {
    this.router.params.subscribe( params => {
      this.task_id = params['id'];
  })


}

  getTask(id: number)
  {
    this.dataService.getTaskByID(id).subscribe((data)=> 
    {
    console.log(data),
    this.task_ = data
  });
  }
  

}
