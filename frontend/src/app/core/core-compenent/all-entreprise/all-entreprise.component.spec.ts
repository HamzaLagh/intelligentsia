import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllEntrepriseComponent } from './all-entreprise.component';

describe('AllEntrepriseComponent', () => {
  let component: AllEntrepriseComponent;
  let fixture: ComponentFixture<AllEntrepriseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllEntrepriseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
