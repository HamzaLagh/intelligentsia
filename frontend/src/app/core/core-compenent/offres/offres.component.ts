import { Component } from '@angular/core';

@Component({
  selector: 'app-offres',
  templateUrl: './offres.component.html',
  styleUrls: ['./offres.component.css']
})
export class OffresComponent {
  fakeArray = new Array(5);
  logoEntreprise : String  = '../../../../assets/images/company.jpg';
  coverTravail : String = '../../../../../assets/images/monde-travail.jpeg'
}
