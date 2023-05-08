import { Component, OnInit } from '@angular/core';
import { Intern } from '../models/intern';
import { InternService } from '../service/intern.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit
{

  signupForm?:FormGroup

  constructor(
    private service: InternService,
    private fb:FormBuilder
  ){}

  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  message:any;

  public registerIntern(addEmployee: NgForm): void
  {
    console.log("Form inputs : "+addEmployee.value)
    this.service.register(addEmployee.value).subscribe(
      (response: any) => {
        console.log("Response from api : "+response)
      }, 
      (error: HttpErrorResponse) =>
      {
        alert(error.message);
      }
    )
  }

  // public registerIntern()
  // {
  //   console.log("Form inputs : "+addEmployee.value)
  //   this.service.register(addEmployee.value).subscribe(
  //     (response: any) => {
  //       console.log("Response from api : "+response)
  //     }, 
  //     (error: HttpErrorResponse) =>
  //     {
  //       alert(error.message);
  //     }
  //   )
  // }
}
