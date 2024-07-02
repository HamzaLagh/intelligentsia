import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SystemeManagementComponent } from './systeme-management.component';

describe('SystemeManagementComponent', () => {
  let component: SystemeManagementComponent;
  let fixture: ComponentFixture<SystemeManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SystemeManagementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SystemeManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
