import { Component, OnInit  } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConnexionService } from '../../core-services/connexion-service';

import { TokenUser } from '../../core-models/tokenUser';
import { PasswordModel } from '../../core-models/passwordModel';   
import  jwt_decode from "jwt-decode";
import { Observable } from 'rxjs';
@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {
form!: FormGroup;
emailCtrl!: FormControl;
passwordCtrl!: FormControl;
token:TokenUser = new TokenUser();
passwordModel:PasswordModel=new PasswordModel();
  id: Number=0;
  constructor(private router : Router,private formBuilder:FormBuilder, private connexionService:ConnexionService,private route: ActivatedRoute) { }

  ngOnInit() : void{ 
    this.route.queryParams.subscribe(params => {
      this.id=params['id'];
  });
    this.initFormControls();
    this.initMainForm();

  }

  private initMainForm(): void{
    this.form= this.formBuilder.group(
      {
        email : this.emailCtrl,
        password : this.passwordCtrl,  
      }
    );
  }


  private initFormControls(): void{
    this.emailCtrl= this.formBuilder.control('',[Validators.required, Validators.email]);
    this.passwordCtrl = this.formBuilder.control('',Validators.required);
  }
    


  goToRegister(){
    this.passwordModel.email=this.form.value.email;
    this.passwordModel.oldPassword=this.form.value.password;
    this.connexionService.login(this.passwordModel).subscribe(
      (response) => {
        // Traitement du contenu de la réponse ici
        this.token.accessToken=response.accessToken;
        this.token.refreshToken=response.refreshToken;
        const decodedToken : any = jwt_decode(this.token.refreshToken);
        const decodedToken1 : any = jwt_decode(this.token.accessToken);
        const obj = { accessToken: this.token.accessToken, refreshToken: this.token.refreshToken, expiration: decodedToken.exp*1000};
        localStorage.setItem('USER-TOKEN-INTELLIGENTSIA', JSON.stringify(obj));
        //console.log(decodedToken1.roles[0],decodedToken1.enabled);

        if(decodedToken1.roles[0]==="STUDENT"){
          if(this.id!=undefined){
            this.router.navigateByUrl(`/OffreInformations?id=${this.id}`);
          }else{
              window.location.replace("/accueil");
            }
        }else if(decodedToken1.roles[0]==="SOCIETY"){
          window.location.replace("/espaceEntreprise"); 
        }
        
        
      },
      (error) => {
        // Gestion des erreurs ici
        //console.error('Erreur:', error);
        //console.log('Statut de l\'erreur:', error.status);
        this.connexionService.enable(this.form.value.email).subscribe(
          (enable:string) =>{
            if(enable==="SOCIETYfalse")
            this.router.navigateByUrl('/attente');
            else if(enable==="STUDENTfalse")
              console.log("Your Compte is not activate");
            else 
            console.log("Your Compte is not exite");
          }
        );

      }
    );
    

  }
}



