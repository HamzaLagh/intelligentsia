import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ConnexionService } from '../../core-services/connexion-service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-confirmation-password',
  templateUrl: './confirmation-password.component.html',
  styleUrls: ['./confirmation-password.component.css']
})
export class ConfirmationPasswordComponent implements OnInit{
  tokenObservable!:Observable<boolean>;
  tokenObservableR!:Observable<boolean>;
  form!: FormGroup;
  confirmationCltr!: FormControl;
  id!:number;
  noConfirme!:boolean;
  confirme!:boolean;

  
  constructor(private formBuilder:FormBuilder,private route: ActivatedRoute,private connexionService: ConnexionService, private router:Router) { }
  
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
        this.id=params['id'];
      this.noConfirme=false;
      this.confirme=false;
      
    });
    this.confirmationCltr= this.formBuilder.control('',Validators.required);
      this.form= this.formBuilder.group(
        {
          confirmation : this.confirmationCltr
        });
  }

 
onClick() {

  this.tokenObservableR=this.connexionService.resendResetPasswordVerifyToken(this.id);
  this.tokenObservableR.subscribe(
    (token : boolean)=>{
     if(token){
     this.confirme=true;
     this.noConfirme=false;
    }
      
    }
  );
}
onSubmit() {
    this.tokenObservable=this.connexionService.verifyResetPassword(this.form.value.confirmation);
    this.tokenObservable.subscribe(
      (token : boolean)=>{
       if(token===true){
        this.router.navigateByUrl(`/new-password?id=${this.id}`);
        this.id=0;
      }
      else{
        this.noConfirme=true;
        this.confirme=false;
      }
      }
    );
  }
  
 
}


