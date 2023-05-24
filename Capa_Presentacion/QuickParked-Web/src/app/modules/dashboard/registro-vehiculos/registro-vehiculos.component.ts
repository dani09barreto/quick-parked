import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'qp-registro-vehiculos',
  templateUrl: './registro-vehiculos.component.html',
  styleUrls: ['./registro-vehiculos.component.scss']
})
export class RegistroVehiculosComponent {
  constructor(private router: Router) {}
  placa: string = '';

  ingresarPlaca():void{

  }

}
