import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environements/environment";
import { Society } from "../core-models/society";

@Injectable()
export class SocietyService {
    storedData: any = localStorage.getItem('USER-TOKEN-INTELLIGENTSIA');
    data: any = JSON.parse(this.storedData);

  constructor(private http: HttpClient) { };

  recupSociety(email: String): Observable<Society> {
    const httpOptions = {
      headers: new HttpHeaders({
        //'content-type' : 'application/json',
        'Authorization': `Bearer ${this.data.accessToken}` 
      })
    };
    return this.http.get<Society>(`${environment.apiUrl}/recupSociety?email=${email}`, httpOptions);
  }
}