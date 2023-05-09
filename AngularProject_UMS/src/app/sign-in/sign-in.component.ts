import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ValidatorFn } from '@angular/forms';
import { InternService } from '../service/intern.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent 
{
  constructor(
    private fb : FormBuilder,
    private service: InternService
  ) {}

  initForm(): FormGroup 
  {
    let form =  this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  
  return form
}
  

  form = this.fb.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  })


  public login(): void
  {
    const body = {
      email: this.form.value.email,
      password: this.form.value.password};

    console.log("Form inputs : "+ body)
    this.service.loginIntern(body).subscribe(
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
