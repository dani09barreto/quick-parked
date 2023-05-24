import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroVehiculosComponent } from './registro-vehiculos.component';

describe('RegistroVehiculosComponent', () => {
  let component: RegistroVehiculosComponent;
  let fixture: ComponentFixture<RegistroVehiculosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistroVehiculosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistroVehiculosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
