import { Component, OnInit } from '@angular/core';
import  jwt_decode from "jwt-decode";
@Component({
  selector: 'app-top-header',
  templateUrl: './top-header.component.html',
  styleUrls: ['./top-header.component.css']
})
export class TopHeaderComponent implements OnInit{

hidden!: boolean;
hidden1!: boolean;
noHidden!: boolean;
ngOnInit(): void {
  const storedData=localStorage.getItem('USER-TOKEN-INTELLIGENTSIA')
  if (storedData) { 
    const data = JSON.parse(storedData);
    const now = Date.now();
    if (now < data.expiration) {
      const decodedToken1 : any = jwt_decode(data.accessToken);
      if(decodedToken1.roles[0]==="STUDENT")
        this.hidden=true;
      else if(decodedToken1.roles[0]==="SOCIETY")
        this.hidden1=true;

      this.noHidden=false;
        }else{
          localStorage.removeItem('USER-TOKEN-INTELLIGENTSIA');
          this.hidden=false;
          this.hidden1=false;
          this.noHidden=true;
        }
              
  }else{
    this.hidden=false; 
    this.hidden1=false;
    this.noHidden=true; 
  }
}
 onLogOut() {
  localStorage.removeItem('USER-TOKEN-INTELLIGENTSIA');
  this.hidden=false;
  this.hidden1=false;
  this.noHidden=true;
}
}
