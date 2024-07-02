import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreRoutingModule } from './core-routing.module';
import { ConnexionComponent } from './core-compenent/connexion/connexion.component';
import { ConfirmationComponent } from './core-compenent/confirmation/confirmation.component';
import { PasswordComponent } from './core-compenent/password/password.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FooterComponent } from './core-compenent/footer/footer.component';
import { OffresComponent } from './core-compenent/offres/offres.component';
import { HeaderComponent } from './core-compenent/header/header.component';
import { EspaceEntrepriseComponent } from './core-compenent/espace-entreprise/espace-entreprise.component';
import { AccueilComponent } from './core-compenent/accueil/accueil.component';
import { NewPasswordComponent } from './core-compenent/new-password/new-password.component';
import { HeaderEntrepriseComponent } from './core-compenent/header-entreprise/header-entreprise.component';
import { ProfilRecruteurComponent } from './core-compenent/profil-recruteur/profil-recruteur.component';
import { TopHeaderComponent } from './core-compenent/top-header/top-header.component';
import { ProfilStagiaireComponent } from './core-compenent/profil-stagiaire/profil-stagiaire.component';
import { GeneralComponent } from './core-compenent/general/general.component';
import { InscriptionService } from './core-services/inscription-service';
import { ConnexionService } from './core-services/connexion-service';
import { AttenteComponent } from './core-compenent/attente/attente.component';
import { DeposerOffreComponent } from './core-compenent/deposer-offre/deposer-offre.component';
import { ProfilOffeComponent } from './core-compenent/profil-offe/profil-offe.component';
import { OffreInformationsComponent } from './core-compenent/offre-informations/offre-informations.component';
import { EditProfilStagiaireComponent } from './core-compenent/edit-profil-stagiaire/edit-profil-stagiaire.component';
import { ConfirmationPasswordComponent } from './core-compenent/confirmation-password/confirmation-password.component';
import { AuthGuard } from './guard/auth.guard';
import { JobOfferService} from './core-services/job-offer-service';
import { NgxPaginationModule } from 'ngx-pagination';
import { StudentService } from './core-services/student-service';
import { PostulerService } from './core-services/postuler-service';
import { SocietyService } from './core-services/society-service';
import { AllEntrepriseComponent } from './core-compenent/all-entreprise/all-entreprise.component';
import { ContactComponent } from './core-compenent/contact/contact.component';
import { FormationsComponent } from './core-compenent/formations/formations.component';
import { CoursComponent } from './core-compenent/cours/cours.component';
import { SystemeManagementComponent } from './core-compenent/systeme-management/systeme-management.component';
import { BimComponent } from './core-compenent/bim/bim.component';
import { RoboticAutomatismeComponent } from './core-compenent/robotic-automatisme/robotic-automatisme.component';
import { ErpComponent } from './core-compenent/erp/erp.component';
import { TransformationsComponent } from './core-compenent/transformations/transformations.component';
import { MaintenanceComponent } from './core-compenent/maintenance/maintenance.component';
import { NavFormationsComponent } from './core-compenent/nav-formations/nav-formations.component';





@NgModule({
  declarations: [
    ConnexionComponent,
    ConfirmationComponent,
    ConfirmationPasswordComponent,
    PasswordComponent,
    FooterComponent,
    HeaderComponent,
    OffresComponent,
    EspaceEntrepriseComponent,
    AccueilComponent,
    OffresComponent,
    NewPasswordComponent,
    HeaderEntrepriseComponent,
    ProfilRecruteurComponent,
    TopHeaderComponent,
    ProfilStagiaireComponent,
    GeneralComponent,
    DeposerOffreComponent,
    OffreInformationsComponent,
    ProfilOffeComponent,
    AttenteComponent,
    EditProfilStagiaireComponent,
    AllEntrepriseComponent,
    ContactComponent,
    FormationsComponent,
    CoursComponent,
    SystemeManagementComponent,
    BimComponent,
    RoboticAutomatismeComponent,
    ErpComponent,
    TransformationsComponent,
    MaintenanceComponent,
    NavFormationsComponent,

  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    CoreRoutingModule,
    HttpClientModule,
    NgxPaginationModule,
    
  ],
  exports: [
    FooterComponent,
    HeaderComponent
  ],
  providers: [
    InscriptionService,
    ConnexionService,
    AuthGuard,
    JobOfferService,
    StudentService,
    PostulerService,
    SocietyService
  ]
})
export class CoreModule { }
