import { TestBed } from '@angular/core/testing';

import { AddCareerNewsService } from './add-career-news.service';

describe('AddCareerNewsService', () => {
  let service: AddCareerNewsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddCareerNewsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
