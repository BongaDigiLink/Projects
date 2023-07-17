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

      return form;
    }

    form = this.initForm()

  public registerIntern(): void
  {
      if(this.initForm().value.password === this.initForm().value.confirmPassword)
      {
        // this.service.register(this.initForm().value).subscribe(
        this.service.register(this.form.value).subscribe(
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
