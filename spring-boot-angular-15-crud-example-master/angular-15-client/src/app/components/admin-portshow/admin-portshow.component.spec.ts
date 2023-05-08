import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPortshowComponent } from './admin-portshow.component';

describe('AdminPortshowComponent', () => {
  let component: AdminPortshowComponent;
  let fixture: ComponentFixture<AdminPortshowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPortshowComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPortshowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
