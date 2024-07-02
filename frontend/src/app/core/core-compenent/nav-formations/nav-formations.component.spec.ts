import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavFormationsComponent } from './nav-formations.component';

describe('NavFormationsComponent', () => {
  let component: NavFormationsComponent;
  let fixture: ComponentFixture<NavFormationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavFormationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavFormationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
