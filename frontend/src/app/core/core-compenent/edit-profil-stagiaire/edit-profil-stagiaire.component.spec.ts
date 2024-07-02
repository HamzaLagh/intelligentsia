import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfilStagiaireComponent } from './edit-profil-stagiaire.component';

describe('EditProfilStagiaireComponent', () => {
  let component: EditProfilStagiaireComponent;
  let fixture: ComponentFixture<EditProfilStagiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditProfilStagiaireComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditProfilStagiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
