import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllVersionsComponent } from './all-versions.component';

describe('AllVersionsComponent', () => {
  let component: AllVersionsComponent;
  let fixture: ComponentFixture<AllVersionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllVersionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllVersionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
