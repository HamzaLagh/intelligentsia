import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environements/environment';

import { Student } from '../core-models/student';
import { StudentModel } from '../core-models/studentModel';
import { SocietyModel } from '../core-models/societyModel';
import { Society } from '../core-models/society';

@Injectable()
export class InscriptionService {


  constructor(private http: HttpClient) { };

  
  saveStudent(studentModel: StudentModel,cv:File):Observable<Student> {
    const formData=new FormData();
    formData.append('studentModelString',JSON.stringify(studentModel));
    formData.append('cv',cv);
    return this.http.post<Student>(`${environment.apiUrl}/registerStudent`, formData);

  }

  saveSociety(societyModel: SocietyModel): Observable<Society> {
    return this.http.post<Society>(`${environment.apiUrl}/registerSociety`,societyModel);
  }
}
