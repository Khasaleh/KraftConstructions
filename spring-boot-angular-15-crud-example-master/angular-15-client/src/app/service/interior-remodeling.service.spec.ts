import { TestBed } from '@angular/core/testing';

import { InteriorRemodelingService } from './interior-remodeling.service';

describe('InteriorRemodelingService', () => {
  let service: InteriorRemodelingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InteriorRemodelingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
