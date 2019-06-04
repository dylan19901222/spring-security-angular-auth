import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthoritieComponent } from './authoritie.component';

describe('AuthoritieComponent', () => {
  let component: AuthoritieComponent;
  let fixture: ComponentFixture<AuthoritieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuthoritieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthoritieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
