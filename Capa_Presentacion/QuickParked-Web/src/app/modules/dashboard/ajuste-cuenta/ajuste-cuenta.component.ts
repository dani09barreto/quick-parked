import { Component } from '@angular/core';
import { DashboardModule } from '../dashboard.module';

@Component({
  selector: 'qp-ajuste-cuenta',
  templateUrl: './ajuste-cuenta.component.html',
  styleUrls: ['./ajuste-cuenta.component.scss']
})
export class AjusteCuentaComponent {
  nombre: string = '';
  cedula: string = '';
  telefono: string = '';
  imagenUrl: string= '';


  actualizar():void{

  }
}
