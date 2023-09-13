import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCSRDataComponent } from './show-csrdata.component';

describe('ShowCSRDataComponent', () => {
  let component: ShowCSRDataComponent;
  let fixture: ComponentFixture<ShowCSRDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowCSRDataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowCSRDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
