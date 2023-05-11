import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminServicePageComponent } from './admin-service-page.component';

describe('AdminServicePageComponent', () => {
  let component: AdminServicePageComponent;
  let fixture: ComponentFixture<AdminServicePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminServicePageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminServicePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
