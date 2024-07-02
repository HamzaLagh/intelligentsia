import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilOffeComponent } from './profil-offe.component';

describe('ProfilOffeComponent', () => {
  let component: ProfilOffeComponent;
  let fixture: ComponentFixture<ProfilOffeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilOffeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilOffeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
