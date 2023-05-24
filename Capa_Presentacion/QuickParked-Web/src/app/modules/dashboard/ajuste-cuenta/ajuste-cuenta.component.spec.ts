import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjusteCuentaComponent } from './ajuste-cuenta.component';

describe('AjusteCuentaComponent', () => {
  let component: AjusteCuentaComponent;
  let fixture: ComponentFixture<AjusteCuentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjusteCuentaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjusteCuentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
