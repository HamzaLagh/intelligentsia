import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JobOffer } from '../../core-models/jobOffers';
import { Observable } from 'rxjs';
import { JobOfferService } from '../../core-services/job-offer-service';
import { Society } from '../../core-models/society';
import  jwt_decode from "jwt-decode";
import { Postuler } from '../../core-models/postuler';
import { StudentService } from '../../core-services/student-service';
import { Student } from '../../core-models/student';
import { PostulerService } from '../../core-services/postuler-service';
import { PostulerModel } from '../../core-models/postulerModel';

@Component({
  selector: 'app-offre-informations',
  templateUrl: './offre-informations.component.html',
  styleUrls: ['./offre-informations.component.css']
})
export class OffreInformationsComponent implements OnInit {
  logoEntreprise : String  = '../../../../assets/images/logoRamaqs.jpeg';
  id!:Number;
  jobOffer:JobOffer=new JobOffer();
  postulerModel: PostulerModel= new PostulerModel();
  isDisabled:boolean=false;
  constructor(private formBuilder:FormBuilder,private jobOfferService:JobOfferService,private route: ActivatedRoute, private router:Router,private studentService:StudentService,private postulerService : PostulerService) { }
  
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id=params['id'];
  });
  this.jobOfferService.jobOffer(this.id).subscribe(
    (offer:JobOffer)=>{
      this.jobOfferService.societyOffer(offer.id).subscribe(
        (society:Society)=>{
          offer.society=society;
        }
      );
      this.jobOffer=offer;
    }
  );

  const storedData=localStorage.getItem('USER-TOKEN-INTELLIGENTSIA')
  if (storedData) { 
    const data = JSON.parse(storedData);
    const now = Date.now();
    if (now < data.expiration) {
      const decodedToken1 : any = jwt_decode(data.accessToken);
      if(decodedToken1.roles[0]==="STUDENT"){
        this.postulerService.recupPostuler(decodedToken1.sub).subscribe(
          (postuler:boolean)=>{
            if(postuler){
              this.isDisabled=true;
            } 
          }
        );
      
        
      }
      
        }else{
          localStorage.removeItem('USER-TOKEN-INTELLIGENTSIA');
          this.router.navigateByUrl(`/connexion?id=${this.id}`);
        }
              
  }else{
    this.router.navigateByUrl(`/connexion?id=${this.id}`);
  }
  


  }
  
  onSubmit(): void{
    const storedData=localStorage.getItem('USER-TOKEN-INTELLIGENTSIA')
  if (storedData) { 
    const data = JSON.parse(storedData);
    const now = Date.now();
    if (now < data.expiration) {
      const decodedToken1 : any = jwt_decode(data.accessToken);
      if(decodedToken1.roles[0]==="STUDENT"){
          this.jobOfferService.jobOffer(this.id).subscribe(
            (offer:JobOffer)=>{
              this.postulerModel.jobOfferId=offer.id;
            }
  
          );
          this.studentService.getStudentEmail(decodedToken1.sub).subscribe(
            (student:Student)=>{
              this.postulerModel.studentId=student.id;
            }
          )
           this.postulerService.savePostuler(this.postulerModel).subscribe(
              (postuler:Postuler)=>{
                if(postuler)
                  this.isDisabled=true;
                  console.log(postuler);
              }
            );
            //console.log(this.postuler);
      }
      
        }else{
          localStorage.removeItem('USER-TOKEN-INTELLIGENTSIA');
          this.router.navigateByUrl(`/connexion?id=${this.id}`);
        }
              
  }else{
    this.router.navigateByUrl(`/connexion?id=${this.id}`);
  }
    
  }
} 
