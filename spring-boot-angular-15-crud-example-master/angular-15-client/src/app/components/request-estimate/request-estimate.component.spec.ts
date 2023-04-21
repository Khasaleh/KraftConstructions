import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestEstimateComponent } from './request-estimate.component';

describe('RequestEstimateComponent', () => {
  let component: RequestEstimateComponent;
  let fixture: ComponentFixture<RequestEstimateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestEstimateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestEstimateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
