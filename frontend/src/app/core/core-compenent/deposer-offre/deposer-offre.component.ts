import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JobOffer } from '../../core-models/jobOffers';
import { SocietyModel } from '../../core-models/societyModel';
import  jwt_decode from "jwt-decode";
import { Society } from '../../core-models/society';
import { JobOfferService } from '../../core-services/job-offer-service';
@Component({
  selector: 'app-deposer-offre',
  templateUrl: './deposer-offre.component.html',
  styleUrls: ['./deposer-offre.component.css']
})
export class DeposerOffreComponent implements OnInit{
  
  form!:FormGroup;
  titreCtrl!:FormControl;
  nbrePosteCtrl!:FormControl;
  dateLimiteCtrl!:FormControl;
  secteurCtrl!:FormControl;
  niveauEtudeCtrl!:FormControl;
  typeContratCtrl!:FormControl;
  typeTravailCtrl!:FormControl;
  regionCtrl!:FormControl;
  softkillsCtrl!:FormControl;
  avantageCtrl!:FormControl;
  descriptionCtrl!:FormControl;
  jobOffer:JobOffer = new JobOffer();
  society:Society= new Society();
  constructor(private formBuilder:FormBuilder,private router: Router,private jobOfferService:JobOfferService){}


  ngOnInit(): void {
    this.initFormControls();
    this.initMainForm();
  }

  private initMainForm(): void{
    this.form= this.formBuilder.group(
      {
        titre : this.titreCtrl,
        nbrePoste : this.nbrePosteCtrl,
        dateLimite : this.dateLimiteCtrl,
        secteur : this.secteurCtrl,
        niveauEtude : this.niveauEtudeCtrl,
        typeContrat : this.typeContratCtrl,
        typeTravail:this.typeTravailCtrl,
        region:this.regionCtrl,
        softkills:this.softkillsCtrl,
        avantage:this.avantageCtrl,
        description:this.descriptionCtrl
        
      }
    );
  }

  private initFormControls(): void{
    this.titreCtrl= this.formBuilder.control('',Validators.required);
    this.nbrePosteCtrl= this.formBuilder.control('',Validators.required);
    this.dateLimiteCtrl= this.formBuilder.control('',Validators.required);
    this.secteurCtrl = this.formBuilder.control('',Validators.required);
    this.niveauEtudeCtrl= this.formBuilder.control('',Validators.required);
    this.typeContratCtrl= this.formBuilder.control('',Validators.required);
    this.typeTravailCtrl= this.formBuilder.control('',Validators.required);
    this.regionCtrl= this.formBuilder.control('',Validators.required);
    this.softkillsCtrl= this.formBuilder.control('',Validators.required);
    this.avantageCtrl= this.formBuilder.control('',Validators.required);
    this.descriptionCtrl= this.formBuilder.control('',Validators.required);
  
  }
  onSubmit(){
    this.jobOffer.titre=this.form.value.titre;
    this.jobOffer.avantage=this.form.value.avantage;
    this.jobOffer.nbrPostePropose=this.form.value.nbrePoste;
   this.jobOffer.niveauEtude=this.form.value.niveauEtude;
    this.jobOffer.dateLimite=this.form.value.dateLimite;
    this.jobOffer.description=this.form.value.description;
    //this.jobOffer.fonction=this.form.value.fonction;
    this.jobOffer.region=this.form.value.region;
    this.jobOffer.secteurActivite=this.form.value.secteur;
    this.jobOffer.softkil=this.form.value.softkills;
    this.jobOffer.typeContrat=this.form.value.typeContrat;
    this.jobOffer.typeDeTravail=this.form.value.typeTravail;
    const storedData=localStorage.getItem('USER-TOKEN-INTELLIGENTSIA')
    if (storedData) { 
      const data = JSON.parse(storedData);
      const now = Date.now();
      if (now < data.expiration) {
        const decodedToken : any = jwt_decode(data.accessToken);
         this.society.email=decodedToken.sub;
         this.jobOffer.society=this.society;
         //console.log(this.jobOffer.Avantage,this.jobOffer.NbrPostePropose);
          this.jobOfferService.saveJobOffer(this.jobOffer).subscribe(
             (jobOffer:JobOffer)=>{
              if(jobOffer!=null)
                console.log(jobOffer);
              else
                console.log("Non");
            }
          );

          }else{
            localStorage.removeItem('USER-TOKEN-INTELLIGENTSIA');
            this.router.navigateByUrl("/connexion");
          }
                
        }
  }
}
