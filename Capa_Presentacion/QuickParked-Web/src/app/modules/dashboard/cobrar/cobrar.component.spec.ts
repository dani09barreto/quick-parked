import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CobrarComponent } from './cobrar.component';

describe('CobrarComponent', () => {
  let component: CobrarComponent;
  let fixture: ComponentFixture<CobrarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CobrarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CobrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
