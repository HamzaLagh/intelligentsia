import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConnexionService } from '../../core-services/connexion-service';
import { PasswordModel } from '../../core-models/passwordModel';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit{
  form!: FormGroup;
  passwordCtrl!: FormControl;
  passwordCtrl1!: FormControl;
  affiche!:boolean;
  id!:Number;
  passwordModel:PasswordModel = new PasswordModel();
  constructor(private router : Router,private formBuilder:FormBuilder, private connexionService:ConnexionService,private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id=params['id'];
    });
    this.initFormControls();
    this.initMainForm();
  }
  private initMainForm(): void{
    this.form= this.formBuilder.group(
      {
        password1 : this.passwordCtrl1,
        password : this.passwordCtrl,  
      }
    );
  }


  private initFormControls(): void {
    this.passwordCtrl1 = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', Validators.required);
  
    // Compare les valeurs des contrôles de formulaire
    if (this.passwordCtrl1.value === this.passwordCtrl.value) {
      this.affiche = false;
    } else {
      this.affiche = true;
    }
  }
  


  onSubmit(){
    if(this.form.value.password1===this.form.value.password){
      if(this.id!=0){
        this.passwordModel.newPassword=this.form.value.password;
        this.passwordModel.idUser=this.id;
        console.log(this.passwordModel.newPassword,this.passwordModel.idUser)
        this.connexionService.savePassword(this.passwordModel).subscribe(
          (rt:boolean)=>{
              if(rt){
                this.router.navigateByUrl("/connexion");
              }else
              this.router.navigateByUrl(`/confirmation?id=${this.id}`);
          }
        );    
        }

    }else
    this.affiche = true;
    
  }
 
}
