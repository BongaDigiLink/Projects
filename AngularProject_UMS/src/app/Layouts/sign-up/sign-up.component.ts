import { Component } from '@angular/core';
import { InternService } from '../../service/intern.service';
import { HttpErrorResponse, HttpHeaderResponse, HttpResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';


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
    password: ['', Validators.required, Validators.min(6)],
    confirmPassword: ['', Validators.required]
  })

  constructor(
    private fb : FormBuilder,
    private service: InternService,
    private router: Router
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

      if(this.form.value.password === this.form.value.confirmPassword)
      {
        this.service.register(body).subscribe(
          (response: string) => {
            //console.log("Response from api : "+response),
            alert('Account Created, continue to login page.');
            this.router.navigate(['/signin']);
          },
          (error: HttpHeaderResponse) =>
          {
            alert(error.status);
          }
        )
      }
      else{
        alert('Passwords do not match!');
      }

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
