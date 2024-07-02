import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilStagiaireComponent } from './profil-stagiaire.component';

describe('ProfilStagiaireComponent', () => {
  let component: ProfilStagiaireComponent;
  let fixture: ComponentFixture<ProfilStagiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilStagiaireComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilStagiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
