import { Component, OnInit } from '@angular/core';
import { JobOfferService } from '../../core-services/job-offer-service';
import { Observable } from 'rxjs';
import { JobOffer } from '../../core-models/jobOffers';
import { Society } from '../../core-models/society';
import {NgxPaginationModule} from 'ngx-pagination'; 
import { Router } from '@angular/router';
@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit{
  logoEntreprise : String  = '../../../../assets/images/logoRmaqs.jpeg';
   fakeArray = new Array(5);
   jobOffers$!:Observable<JobOffer[]>;
   jobOffers!:JobOffer[];
   p:number=1;
   itemsPerPage:number=4;
   totalJobOffer:any;
   constructor(private jobOfferService:JobOfferService,private router:Router){}

   ngOnInit() {
    this.jobOffers = [];
    this.jobOffers$=this.jobOfferService.recupererAllNewJobOffer();
    this.jobOffers$.subscribe(
     (data:JobOffer[])=>{
        data.forEach(jobOffer => {
          this.jobOfferService.societyOffer(jobOffer.id).subscribe(
            (society:Society)=>{
              jobOffer.society=society;
            }
          );
          this.jobOffers.push(jobOffer);
        });
        
      }
    );
    this.totalJobOffer=this.jobOffers.length;

   }

   onSubmit(id:Number){
   this.router.navigateByUrl(`/OffreInformations?id=${id}`);
    

   }
}
