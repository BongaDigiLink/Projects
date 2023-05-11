import { Component } from '@angular/core';
import { MentorService } from '../service/mentor.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent 
{
  public edit = false;
  constructor(
    private fb : FormBuilder,
    private mentorService: MentorService
  ){}

  onClickEdit()
  {
    if(this.edit === false)
    {
    this.edit = true;
    }else{
      this.edit = false;
    }
  }

  form = this.fb.group({
    email: [''],
    name:[''],
    surname:[''],
    trainingField:[''],
    role:['']
  })

  initForm(): FormGroup {
    let form =  this.fb.group({
      email: ['', Validators.required],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      trainingField: ['', Validators.required],
      role:['', Validators.required]
    })

    //Check whether email is not used by other account.
    //checkPassword()
    //If valid return form.

    return form;
  }

  public updateIntern(id: any): void
  {
    const body = {
      email: this.form.value.email,
      name: this.form.value.name,
      surname: this.form.value.surname,
      trainingField: this.form.value.trainingField
    }

    console.log("Form inputs : "+ body)
    this.mentorService.updateInternDetails(id,body).subscribe(
      (response: any) => {
        console.log("Response from api : "+response)
      }, 
      (error: HttpErrorResponse) =>
      {
        alert(error.message);
      }
    )
  }

  public upDate(id: any): void{}


}
