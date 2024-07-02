import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './core-compenent/accueil/accueil.component';
import { ConfirmationComponent } from './core-compenent/confirmation/confirmation.component';
import { ConnexionComponent } from './core-compenent/connexion/connexion.component';
import { DeposerOffreComponent } from './core-compenent/deposer-offre/deposer-offre.component';
import { EditProfilStagiaireComponent } from './core-compenent/edit-profil-stagiaire/edit-profil-stagiaire.component';
import { EspaceEntrepriseComponent } from './core-compenent/espace-entreprise/espace-entreprise.component';
import { GeneralComponent } from './core-compenent/general/general.component';
import { HeaderEntrepriseComponent } from './core-compenent/header-entreprise/header-entreprise.component';
import { HeaderComponent } from './core-compenent/header/header.component';
import { NewPasswordComponent } from './core-compenent/new-password/new-password.component';
import { OffreInformationsComponent } from './core-compenent/offre-informations/offre-informations.component';
import { OffresComponent } from './core-compenent/offres/offres.component';
import { PasswordComponent } from './core-compenent/password/password.component';
import { ProfilOffeComponent } from './core-compenent/profil-offe/profil-offe.component';
import { ProfilRecruteurComponent } from './core-compenent/profil-recruteur/profil-recruteur.component';
import { ProfilStagiaireComponent } from './core-compenent/profil-stagiaire/profil-stagiaire.component';
import { ConfirmationPasswordComponent } from './core-compenent/confirmation-password/confirmation-password.component';
import { AttenteComponent } from './core-compenent/attente/attente.component';
import { AuthGuard } from './guard/auth.guard';
import { AllEntrepriseComponent } from './core-compenent/all-entreprise/all-entreprise.component';
import { ContactComponent } from './core-compenent/contact/contact.component';
import { FormationsComponent } from './core-compenent/formations/formations.component';
import { CoursComponent } from './core-compenent/cours/cours.component';
import { ErpComponent } from './core-compenent/erp/erp.component';
import { MaintenanceComponent } from './core-compenent/maintenance/maintenance.component';
import { BimComponent } from './core-compenent/bim/bim.component';
import { TransformationsComponent } from './core-compenent/transformations/transformations.component';
import { RoboticAutomatismeComponent } from './core-compenent/robotic-automatisme/robotic-automatisme.component';
import { SystemeManagementComponent } from './core-compenent/systeme-management/systeme-management.component';


const routes: Routes = [
  {path:"", component:AccueilComponent},
  {path:"connexion", component:ConnexionComponent },
  {path:"confirmation", component:ConfirmationComponent},
  {path:"password", component:PasswordComponent},
  {path:"general", component:GeneralComponent},
  {path:"accueil", component:AccueilComponent},
  {path:"offres",component:OffresComponent},
  {path:"profil-stagiaire", component:ProfilStagiaireComponent},
  {path:"espaceEntreprise", component:EspaceEntrepriseComponent},
  //{path:"espaceEntreprise", component:EspaceEntrepriseComponent,canActivate: [AuthGuard]},
  {path:"header",component:HeaderComponent},
  {path:"new-password",component:NewPasswordComponent},
  {path:"headerEntreprise",component:HeaderEntrepriseComponent},
  {path:"profil-recruteur", component:ProfilRecruteurComponent},
  {path:"deposerOffre", component:DeposerOffreComponent},
  //{path:"deposerOffre", component:DeposerOffreComponent, canActivate: [AuthGuard] },
  {path:"OffreInformations", component:OffreInformationsComponent},
  {path:"profilOffre", component:ProfilOffeComponent},
  {path:"attente", component:AttenteComponent},
  {path:"Edit-profil-stagiaire", component:EditProfilStagiaireComponent},
  {path:"confirmationpassword",component:ConfirmationPasswordComponent},
  {path:"allEntreprise",component:AllEntrepriseComponent},
  {path:"contact",component:ContactComponent},
  {path:"formations",component:FormationsComponent},
  {path:"cours",component:CoursComponent},
  // Les Formations
  {path:"ERP",component:ErpComponent},
  {path:"Maintanance",component:MaintenanceComponent},
  {path:"BIM",component:BimComponent},
  {path:"TRANSFORMATION-DIGITALE",component:TransformationsComponent},
  {path:"RoboticAutomatisme",component:RoboticAutomatismeComponent},
  {path:"systemeManagement",component:SystemeManagementComponent},
  


  




];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }
