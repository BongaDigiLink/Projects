import { Component, OnInit } from '@angular/core';
import { InternService } from '../../service/intern.service';
import { HttpErrorResponse, HttpHeaderResponse, HttpResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, FormControl, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit
{

  constructor(
    private service: InternService,
    private router: Router
  ){}

  signupform = new FormGroup({
    email: new FormControl('',[Validators.required]),
    name: new FormControl ('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    trainingField: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.min(6)]),
    confirmPassword: new FormControl('', [Validators.required])
  })

  ngOnInit(): void {
    console.log(this.signupform.value);
  }

  registerIntern(): void
  {
    console.log(this.signupform.value)

      if(this.signupform.get('password') === this.signupform.get('confirmPassword'))
      {
        this.service.register(this.signupform.value).subscribe(
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

}

