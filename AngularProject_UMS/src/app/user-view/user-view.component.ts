import { Component, OnInit } from '@angular/core';
import { MentorService } from '../service/mentor.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Intern } from '../models/intern';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit
{
  public edit = false;
  public userView: Intern = new Intern;
  public userId!: Number;


  constructor(
    private fb : FormBuilder,
    private mentorService: MentorService,
    private router: ActivatedRoute
  ){}


  ngOnInit(): void {
    this.router.params.subscribe( params => {
      this.userId = params['id'];
    });

    this.mentorService.getInternDetailsById(this.userId).subscribe( data => 
      {
        console.log("Data (user) from API "+data);
        this.userView = data;
      }, (err: any) => {
        console.log('Could not retrieve this user '+ this.userId)
      })
  }

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
    activeStatus:['']
  })

  initForm(): FormGroup {
    let form =  this.fb.group({
      email: ['', Validators.required, Validators.email],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      trainingField: ['', Validators.required],
      activeStatus:[this.userView.activeStatus, Validators.required]
    })

    //Check whether email is not used by other account.
    //checkPassword()
    //If valid return form.

    return form;
  }

  public updateIntern(): void
  {
    const body = {
      email: this.form.value.email,
      name: this.form.value.name,
      surname: this.form.value.surname,
      activeStatus: this.form.value.activeStatus,
      trainingField: this.form.value.trainingField
    }

    this.mentorService.updateInternDetails(this.userId,body).subscribe(
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
