import { Component } from '@angular/core';
import { Intern } from '../models/intern';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent 
{
  constructor(
    private registrationService:RegistrationService
  ){}

  intern: Intern = new Intern("@newUser","bonga@mail.com","Bonga","Gougota",56664,"NewPassword0");
  message:any;

  public registerIntern()
  {
    console.log("Btn clicked")
    
    let response = this.registrationService.registration(new Intern("@newUser","bonga@mail.com","Bonga","Gougota",56664,"NewPassword0"));
    //response.subscribe((data)=>this.message=data);
    //let response = this.registrationService.registration(intern)
  }
}
