import { Component } from '@angular/core';
import { DashboardModule } from '../dashboard.module';

import { MatDialog, MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'qp-cobrar',
  templateUrl: './cobrar.component.html',
  styleUrls: ['./cobrar.component.scss']
})
export class CobrarComponent {
  constructor(private dialogRef: MatDialogRef<CobrarComponent>) { }

  placa : string = '';
 valorIngresado: string = '';
  tipoVehiculo : string ='';
  horaIngreso! : Date;
  tarifa! : number;
  valor! : number;
  recargoReserva! : number;
  total! :number;
  placaTouched: boolean = false;
  regex: string = '^[A-Za-z]{3}\\d{3}$'; // Expresión regular por defecto para 'Carro'

  placaValida(): boolean{
    const regex = new RegExp(this.regex);
    return regex.test(this.placa);
  }
  updateRegex(option: string): void {
    if (option === 'carro') {
      this.regex = '^[A-Za-z]{3}\\d{3}$'; // Expresión regular para 'Carro'
    } 
    if (option === 'moto') {
      this.regex = '^[A-Z]{3}\\d{2}[A-Z]$'; // Expresión regular para 'Moto'
    }
  }
  convertToUppercase() {
    this.placa = this.placa.toUpperCase();
  }
  
  buscar() : void {

  }

  cobrar() : void{

  }
  goBack() {
    this.dialogRef.close();
  }
}
