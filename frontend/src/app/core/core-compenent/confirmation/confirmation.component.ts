import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ConnexionService } from '../../core-services/connexion-service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit{
  tokenObservable!:Observable<boolean>;
  tokenObservableR!:Observable<boolean>;
  form!: FormGroup;
  confirmationCltr!: FormControl;
  studentId!:number;
  noConfirme!:boolean;
  confirme!:boolean;

  
  constructor(private formBuilder:FormBuilder,private route: ActivatedRoute,private connexionService: ConnexionService, private router:Router) { }
  
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.studentId = params['id']; 
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
this.noConfirme=false;
this.tokenObservableR=this.connexionService.resendVerifyToken(this.studentId);
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
  this.tokenObservable=this.connexionService.verifyRegistration(this.form.value.confirmation);
  this.tokenObservable.subscribe(
    (token : boolean)=>{
     if(token){
      this.router.navigateByUrl("/connexion");
      this.studentId=0;
    }
    else{
      this.noConfirme=true;
      this.confirme=false;
    }
    }
  );
}

}
