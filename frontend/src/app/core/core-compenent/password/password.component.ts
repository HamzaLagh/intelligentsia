import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConnexionService } from '../../core-services/connexion-service';
import { Observable } from 'rxjs';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent implements OnInit{
  form!: FormGroup;
  emailCtrl!: FormControl;
  url!: Observable<number>;
  affiche!:boolean;
  constructor(private router : Router,private formBuilder:FormBuilder, private connexionService:ConnexionService) { }
  ngOnInit(): void {
    this.initFormControls();
    this.initMainForm();
    this.affiche=false;
  }
  private initMainForm(): void{
    this.form= this.formBuilder.group(
      {
        email : this.emailCtrl,
      }
    );
  }


  private initFormControls(): void{
    this.emailCtrl= this.formBuilder.control('',[Validators.required, Validators.email]);
  }

  onSubmit(){
    this.url=this.connexionService.emailControle(this.form.value.email)
    this.url.subscribe(
      (id:number)=>{
        if(id===0){
          this.affiche=true;
        }else{
          this.router.navigateByUrl(`/confirmationpassword?id=${id}`);
        }
      }
    );
  }
}
