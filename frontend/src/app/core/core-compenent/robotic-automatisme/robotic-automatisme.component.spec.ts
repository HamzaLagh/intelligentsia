import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoboticAutomatismeComponent } from './robotic-automatisme.component';

describe('RoboticAutomatismeComponent', () => {
  let component: RoboticAutomatismeComponent;
  let fixture: ComponentFixture<RoboticAutomatismeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoboticAutomatismeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoboticAutomatismeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
