import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environements/environment";
import { JobOffer } from "../core-models/jobOffers";
import { Router } from "@angular/router";
import  jwt_decode from "jwt-decode";
import { Society } from "../core-models/society";
import { Postuler } from "../core-models/postuler";
@Injectable()
export class JobOfferService {
     storedData: any = localStorage.getItem('USER-TOKEN-INTELLIGENTSIA');
     data: any = JSON.parse(this.storedData);
    //decodedToken: any = jwt_decode(this.data.accessToken);
  
    constructor(private http: HttpClient) {}
  
    saveJobOffer(jobOffer: JobOffer): Observable<JobOffer> {
     const httpOptions = {
        headers: new HttpHeaders({
          //'content-type' : 'application/json',
          'Authorization': `Bearer ${this.data.accessToken}` 
        })
      };
      return this.http.post<JobOffer>(`${environment.apiUrl}/registerOffer`, jobOffer, httpOptions);
      
    }

   

    recupererAllNewJobOffer():Observable<JobOffer[]>{
      return this.http.get<JobOffer[]>(`${environment.apiUrl}/AllJobOfferNew`);
    }

    societyOffer(id: Number):Observable<Society>{
      return this.http.get<Society>(`${environment.apiUrl}/societyOffer?id=${id}`);
    }
    jobOffer(id: Number):Observable<JobOffer>{
      return this.http.get<JobOffer>(`${environment.apiUrl}/getOffer?id=${id}`);
    }
    
}