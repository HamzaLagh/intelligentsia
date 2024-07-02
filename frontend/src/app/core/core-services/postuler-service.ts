import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environements/environment";
import { Postuler } from "../core-models/postuler";
import { PostulerModel } from "../core-models/postulerModel";

@Injectable()
export class PostulerService {
    storedData: any = localStorage.getItem('USER-TOKEN-INTELLIGENTSIA');
    data: any = JSON.parse(this.storedData);

  constructor(private http: HttpClient) { };



  savePostuler(postulerModel: PostulerModel): Observable<Postuler> {
    const httpOptions = {
      headers: new HttpHeaders({
         'content-type' : 'application/json',
         'Authorization': `Bearer ${this.data.accessToken}` 
      })
    };
    return this.http.post<Postuler>(`${environment.apiUrl}/registerPostuler`, postulerModel, httpOptions);
  
  }

  recupPostuler(email: String): Observable<boolean> {
    const httpOptions = {
      headers: new HttpHeaders({
        //'content-type' : 'application/json',
        'Authorization': `Bearer ${this.data.accessToken}` 
      })
    };
    return this.http.get<boolean>(`${environment.apiUrl}/recupPostuler?email=${email}`, httpOptions);
  }
  recupAllPostulerSociety(id:Number):Observable<Postuler[]>{
    const httpOptions = {
      headers: new HttpHeaders({
        //'content-type' : 'application/json',
        'Authorization': `Bearer ${this.data.accessToken}` 
      })
    };
    return this.http.get<Postuler[]>(`${environment.apiUrl}/recupAllPostulerSociety?id=${id}`, httpOptions);
  }
}
