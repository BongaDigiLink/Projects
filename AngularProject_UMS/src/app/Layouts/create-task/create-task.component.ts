import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MentorService } from '../../service/mentor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent
{

  constructor(private fb: FormBuilder,
    private mentorService: MentorService,
    private router: Router){}

    form = this.fb.group({
      taskTitle: ['', Validators.required],
      trainingField: ['', Validators.required],
      dueDate:['', Validators.required],
      taskDescription: ['', Validators.required]
    })

  initForm(): FormGroup {
    let form =  this.fb.group({
      taskTitle: ['', Validators.required],
      trainingField: ['', Validators.required],
      dueDate: ['', Validators.required],
      taskDescription: ['', Validators.required],
    })

    return form;
  }

  createTask()
  {
    const body = {
      name: this.form.value.taskTitle,
      dueDate: this.form.value.dueDate,
      fieldTraining: this.form.value.trainingField,
      description: this.form.value.taskDescription
    }

    this.mentorService.createTask(body).subscribe( data =>
     //console.log("API response: "+data)),
      this.router.navigate(['/dashboard']))
  }

}
