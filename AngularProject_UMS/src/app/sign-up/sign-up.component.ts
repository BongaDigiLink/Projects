import { Component } from '@angular/core';
import { InternService } from '../service/intern.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, ValidatorFn } from '@angular/forms';


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent
{

  form = this.fb.group({
    email: ['', Validators.required],
    name: ['', Validators.required],
    surname: ['', Validators.required],
    trainingField: ['', Validators.required],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  })

  constructor(
    private fb : FormBuilder,
    private service: InternService
  ){}
  
    initForm(): FormGroup {
      let form =  this.fb.group({
        email: ['', Validators.required],
        name: ['', Validators.required],
        surname: ['', Validators.required],
        trainingField: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required]
      })
      //form.addValidators()
      //add validation to compare passwords
      //form.addValidators(this.matchValidator(form.get('password').value, form.get('confirmPassword').value));

      //If valid return form.
      return form;
    }

  public registerIntern(): void
  {
    const body = {
      email: this.form.value.email,
      name: this.form.value.name,
      surname: this.form.value.surname,
      trainingField: this.form.value.trainingField,
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

  matchValidator(
    passwordOne: string | null,
    passwordTwo: string | null
  ): ValidatorFn {
    return () => {
      if (passwordOne?.valueOf !== passwordTwo?.valueOf)
        return { match_error: 'Passwords do not match' };
      return null;
    };
  }

}
