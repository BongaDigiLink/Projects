import { Component, OnInit } from '@angular/core';
import { Intern } from '../models/intern';
import { InternService } from '../service/intern.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent
{
  addIntern: Intern = {
    name: '',
    email: '',
    surname: '',
    password: ''
  };

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', Validators.required],
    surname: ['', Validators.required],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  })

  constructor(
    private fb : FormBuilder,
    private service: InternService
  ){}

  message:any;
  
    initForm(): FormGroup {
      let form =  this.fb.group({
        name: ['', Validators.required],
        email: ['', Validators.required],
        surname: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required]
      })
      //form.addValidators()
      //add validation to compare passwords

      //If valid return form.
      return form;
    }

  public registerIntern(): void
  {

    const body = {
      email: this.form.value.email,
      name: this.form.value.name,
      surname: this.form.value.surname,
      password: this.form.value.password};

    console.log("Form inputs : "+ body)
    this.service.register(body).subscribe(
      (response: any) => {
        console.log("Response from api : "+response)
      }, 
      (error: HttpErrorResponse) =>
      {
        alert(error.message);
      }
    )
  }

}
