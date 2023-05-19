import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Skills } from 'src/app/models/skills';
import { MentorService } from 'src/app/service/mentor.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit{

  constructor(private dataService: MentorService,
    private router: Router,
    private activatedRouter: ActivatedRoute){}

    public tasks: Skills[] = [];


  ngOnInit(): void {
    this.getAllTasks();
  }

  /**
   * capture all the tasks issued from db.
   */
  getAllTasks()
  {
    this.dataService.getTasks().subscribe((data)=> {
      console.log(data);
      this.tasks = data;
    })
  }

  viewTask(id: number | undefined)
  {
    this.router.navigate([`/task/${id}`]).then(data => console.log("data on: "+data));
  }


}
