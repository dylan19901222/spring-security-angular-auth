import { TestBed, inject } from '@angular/core/testing';

import { AuthoritieTestService } from './authoritie-test.service';

describe('AuthoritieTestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthoritieTestService]
    });
  });

  it('should be created', inject([AuthoritieTestService], (service: AuthoritieTestService) => {
    expect(service).toBeTruthy();
  }));
});
