import { Component } from '@angular/core';
import { DashboardModule } from '../dashboard.module';

import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { VehiculosService } from 'src/app/services/vehiculos.service';
import { PagosfacturasService } from 'src/app/services/pagosfacturas.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'qp-cobrar',
  templateUrl: './cobrar.component.html',
  styleUrls: ['./cobrar.component.scss'],
})
export class CobrarComponent {
  constructor(
    private dialogRef: MatDialogRef<CobrarComponent>,
    private vehiculoService: VehiculosService,
    private pagosFacturasService: PagosfacturasService
  ) {}
  datePipe = new DatePipe('en-US');

  placa: string = '';
  valorIngresado: string = '';
  tipoVehiculo: string = '';
  horaIngreso!: string;
  tarifa!: number;
  valor!: number;
  recargoReserva!: number;
  total!: number;
  placaTouched: boolean = false;
  regex: string = '^[A-Za-z]{3}\\d{3}$'; // Expresión regular por defecto para 'Carro'

  placaValida(): boolean {
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

  obtenerFactura(): void {
    this.pagosFacturasService.obtenerFactura(this.placa).subscribe(
      (respuesta) => {
        // Aquí puedes manejar la respuesta del backend
        this.placa = respuesta.placa;
        this.valorIngresado = respuesta.valor.toString();
        this.tipoVehiculo = respuesta.tipoVehiculo;
        const fechaHoraIngreso = new Date(respuesta.horaIngreso);
        const hora = fechaHoraIngreso.getHours();
        const minutos = fechaHoraIngreso.getMinutes();

        this.horaIngreso = `${hora}:${minutos < 10 ? '0' : ''}${minutos}`;

        this.tarifa = respuesta.tarifa;
        this.valor = respuesta.valor;
        this.recargoReserva = respuesta.montoReserva;
        this.total = this.valor + this.recargoReserva;
      },
      (error) => {
        // Aquí puedes manejar los errores de la solicitud
        error(error);
      }
    );
  }

  cobrar(): void {
    this.pagosFacturasService
      .realizarPago(this.placa, Number(this.valorIngresado))
      .subscribe(
        (respuesta) => {
          // Aquí puedes manejar la respuesta del servidor
          alert(`Tiene que dar de vueltas: ${respuesta}`);
          this.dialogRef.close();
        },
        (error) => {
          // Aquí puedes manejar los errores de la solicitud
          error(error);
        }
      );
  }
  goBack() {
    this.dialogRef.close();
  }
}
