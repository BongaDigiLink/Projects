import { Component, OnInit } from '@angular/core';
import { MentorService } from '../../service/mentor.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Skills } from 'src/app/models/skills';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit
{
  public edit = false;
  public task: Skills = new Skills;
  public taskId!: number;
  public viewElem: boolean = false;
  public viewElemS: boolean = false;
  private email_: string | undefined;


  constructor(
    private authService: AuthService,
    private fb : FormBuilder,
    private mentorService: MentorService,
    private router: ActivatedRoute,
    private router_: Router
  ){}
  


  ngOnInit(): void {
    this.router.params.subscribe( params => {
      this.taskId = params['id'];
    });

    this.checkUserAuth();

    this.mentorService.getTaskByID(this.taskId).subscribe( data =>
      {
        this.task = data;
        console.log(data);

        this.form.setValue({
          taskTitle:`${this.task.name}`,
          taskDescription:`${this.task.description}`,
          dueDate:`${this.task.dueDate}`,
          trainingField:`${this.task.fieldTraining}`,
      });

      }, (err: any) => 
      {
        console.log('Could not retrieve this user '+ this.taskId)
      })

  }

  public checkUserAuth()
  {
    if(sessionStorage.getItem('user_modifier') === "Mentor")
    {
      this.viewElem = false;
      this.viewElemS = true;
      
    }
    else{
      this.viewElem = true;
    this.viewElemS = false;
    }

  }


  public form = this.fb.group({
    taskTitle:[''],
    taskDescription:[''],
    trainingField:[''],
    dueDate:[''],
  })

  initForm(): FormGroup 
  {
    let form =  this.fb.group({
      taskTitle: ['', Validators.required],
      taskDescription: ['', Validators.required],
      trainingField: ['', Validators.required],
      dueDate:['', Validators.required]
    })

    return form;
  }

  //Admin update function.
  public updateTask()
  {
    const body = {
      name: this.form.value.taskTitle,
      description: this.form.value.taskDescription,
      dueDate: this.form.value.dueDate,
      trainingField: this.form.value.trainingField
    }

    this.mentorService.updateSkill(this.taskId, body).subscribe((data) => {
      console.log(data);

      alert("This task has been updated.");

      this.router_.navigate(['/dashboard']);
    })
    
  }

    //Intern user, mark this as completed an remove from task view.
    public markDone()
    {
      const body = {
        email: sessionStorage.getItem('user_email')?.toString(),
        name: this.form.value.taskTitle,
      description: this.form.value.taskDescription,
      dueDate: this.form.value.dueDate,
      trainingField: this.form.value.trainingField
      }
  
     this.email_ = sessionStorage.getItem('user_email')?.toString();
     console.log(body);
  
      this.mentorService.updateTask(this.taskId, body);
      alert("This task has been submitted. Marked done.");
      this.router_.navigate(['/tasks']);
    }

}