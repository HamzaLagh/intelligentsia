import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OffreInformationsComponent } from './offre-informations.component';

describe('OffreInformationsComponent', () => {
  let component: OffreInformationsComponent;
  let fixture: ComponentFixture<OffreInformationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OffreInformationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OffreInformationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
