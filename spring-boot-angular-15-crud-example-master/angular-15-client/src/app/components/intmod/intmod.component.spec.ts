import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntmodComponent } from './intmod.component';

describe('IntmodComponent', () => {
  let component: IntmodComponent;
  let fixture: ComponentFixture<IntmodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IntmodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IntmodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
