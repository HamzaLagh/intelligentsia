import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header-entreprise',
  templateUrl: './header-entreprise.component.html',
  styleUrls: ['./header-entreprise.component.css']
})
export class HeaderEntrepriseComponent implements OnInit {
  image : String  = '../../../../assets/images/logo.png';
  ngOnInit() {}

}
