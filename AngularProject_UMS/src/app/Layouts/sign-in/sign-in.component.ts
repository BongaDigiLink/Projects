import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ValidatorFn } from '@angular/forms';
import { InternService } from '../../service/intern.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Intern } from '../../models/intern';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})

export class SignInComponent
{
  constructor(
    private fb : FormBuilder,
    private service: InternService,
    private router: Router,
    private authService: AuthService,
  ) {}

  initForm(): FormGroup
  {
    let form =  this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })

  return form
}

  form = this.initForm()

  public login(): void
  {

    this.service.loginIntern(this.form.value).subscribe( (response: Intern) => {
        console.log((response.activeStatus?.toString() === "ACTIVE"));
        if(response.activeStatus?.toString() === "ACTIVE")
        {
          //console.log("Logged in user status : "+response.activeStatus)
          sessionStorage.setItem('user_email',`${response.email}`)
          sessionStorage.setItem('user_id',`${response.id}`)

          this.authService.loginUser();
          //alertifyjs.success('message');

          this.router.navigate(['/dashboard']);
        }
        else {
          alert("Unauthorised Access, Please contact your System Admin at Admin@digilink.africa")
        }
        
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
