import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddCareersNewsComponent } from './admin-add-careers-news.component';

describe('AdminAddCareersNewsComponent', () => {
  let component: AdminAddCareersNewsComponent;
  let fixture: ComponentFixture<AdminAddCareersNewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAddCareersNewsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAddCareersNewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
