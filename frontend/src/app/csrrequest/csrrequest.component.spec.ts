import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CsrrequestComponent } from './csrrequest.component';

describe('CsrrequestComponent', () => {
  let component: CsrrequestComponent;
  let fixture: ComponentFixture<CsrrequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CsrrequestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CsrrequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
