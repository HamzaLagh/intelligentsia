import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OffresComponent } from './core/core-compenent/offres/offres.component';

const routes: Routes = [
  {path:"",loadChildren:() => import('./core/core.module').then(m => m.CoreModule)},
  {path:"offres", component:OffresComponent },
 

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
