import { environment } from "src/environements/environment";
import { Student } from "../core-models/student";
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class StudentService {
    storedData: any = localStorage.getItem('USER-TOKEN-INTELLIGENTSIA');
    data: any = JSON.parse(this.storedData);

  constructor(private http: HttpClient) { };



  getStudentEmail(email: String): Observable<Student> {
    const httpOptions = {
        headers: new HttpHeaders({
          //'content-type' : 'application/json',
          'Authorization': `Bearer ${this.data.accessToken}` 
        })
      };

    return this.http.get<Student>(`${environment.apiUrl}/getStudent?email=${email}`,httpOptions);
  }
}
