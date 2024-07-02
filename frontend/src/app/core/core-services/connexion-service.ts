import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environements/environment";
import { PasswordModel } from "../core-models/passwordModel";

@Injectable()
export class ConnexionService {

  


  constructor(private http: HttpClient) { };


  verifyRegistration(token: string): Observable<boolean>{
    return this.http.get<boolean>(
      `${environment.apiUrl}/verifyRegistration?token=${token}`
    );
  }
  resendVerifyToken(id: number): Observable<boolean>{
    return this.http.get<boolean>(
      `${environment.apiUrl}/resendVerifyToken?id=${id}`
    );
  }
  login(passwordModel:PasswordModel):Observable<any>{
    return this.http.post<any>(`${environment.apiUrl}/login`,passwordModel);
  }
  emailControle(email: string): Observable<number>{
    return this.http.get<number>(
      `${environment.apiUrl}/resetPassword?email=${email}` 
    );
  }

  verifyResetPassword(token: string): Observable<boolean>{
    return this.http.get<boolean>(
      `${environment.apiUrl}/verifyResetPassword?token=${token}`
    );
  }
  resendResetPasswordVerifyToken(id: number): Observable<boolean> {
    return this.http.get<boolean>(
      `${environment.apiUrl}/resendResetPasswordVerifyToken?id=${id}` 
    );
  }
  
  savePassword(passwordModel: PasswordModel): Observable<boolean> {
    return this.http.post<boolean>(`${environment.apiUrl}/savePassword`, passwordModel);
  }
  enable(email:string):Observable<string>{
    return this.http.get<string>(
      `${environment.apiUrl}/enable?email=${email}`,{ responseType: 'text' as 'json' }
    );
  }
}