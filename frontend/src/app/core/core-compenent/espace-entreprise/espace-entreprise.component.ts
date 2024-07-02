import { Component, OnInit } from '@angular/core';
import  jwt_decode from "jwt-decode";
import { Society } from '../../core-models/society';
import { SocietyService } from '../../core-services/society-service';
import { Postuler } from '../../core-models/postuler';
import { PostulerService } from '../../core-services/postuler-service';
import { JobOfferService } from '../../core-services/job-offer-service';
@Component({
  selector: 'app-espace-entreprise',
  templateUrl: './espace-entreprise.component.html',
  styleUrls: ['./espace-entreprise.component.css']
})
export class EspaceEntrepriseComponent implements OnInit {
  
  image : String  = '../../../../assets/images/ProfileProf.jpg';
  CvExemple : String  = '../../../../assets/images/CvExemple.png';
  fakeArray = new Array(5);
  p:number=1;
  itemsPerPage:number=4;
  totalPostuler:any;
  postulers!:Postuler[];
  showCvFlag = false;

  
  constructor(private societyService:SocietyService,private postulerService:PostulerService,private jobOfferService:JobOfferService){}
  ngOnInit() {
    this.postulers=[];
    const storedData=localStorage.getItem('USER-TOKEN-INTELLIGENTSIA')
    if (storedData) { 
      const data = JSON.parse(storedData);
      const now = Date.now();
      if (now < data.expiration) {
        const decodedToken1 : any = jwt_decode(data.accessToken);
         if(decodedToken1.roles[0]==="SOCIETY"){
          this.societyService.recupSociety(decodedToken1.sub).subscribe(
            (society:Society)=>{
              this.postulerService.recupAllPostulerSociety(society.id).subscribe(
                (data:Postuler[]) => {data.forEach(postule=>
                  {postule.jobOffer.society=society;
                    this.postulers.push(postule)});}
              );
            }
          ); 
          this.totalPostuler=this.postulers.length;
         }
          
          }else{
            localStorage.removeItem('USER-TOKEN-INTELLIGENTSIA');
          }
                
    }
  }
  onShowCv() {
    this.showCvFlag = true;
  }
}
