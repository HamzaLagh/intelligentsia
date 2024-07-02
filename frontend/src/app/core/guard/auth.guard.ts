import { Injectable } from "@angular/core";
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import  jwt_decode from "jwt-decode";
@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) {}
  
canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        const storedData=localStorage.getItem('USER-TOKEN-INTELLIGENTSIA');
        if (storedData) { 
            const data = JSON.parse(storedData);
            const now = Date.now();
            if (now < data.expiration) {
                const decodedToken1 : any = jwt_decode(data.accessToken);
                if(decodedToken1.roles[0]==="SOCIETY")
                    return true;
                 else {
                    this.router.navigateByUrl('/connexion');
                    return false;}
             }else{
                this.router.navigateByUrl('/connexion');
                return false;}
        }else{
            this.router.navigateByUrl('/connexion');
                return false;
        }

}



  }