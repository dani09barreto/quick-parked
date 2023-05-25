import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardModule } from '../dashboard.module';
import { VehiculosService } from 'src/app/services/vehiculos.service';
import { tipoVehiculo } from 'src/app/shared/model/tipo.vehiculo';
@Component({
  selector: 'qp-registro-vehiculos',
  templateUrl: './registro-vehiculos.component.html',
  styleUrls: ['./registro-vehiculos.component.scss']
})
export class RegistroVehiculosComponent {
  constructor(private router: Router, private vehiculoService:VehiculosService) {}
  placa: string = '';
  cuposDisponibles: string = 'Cupos Disponibles: ';
  cuposReservados: string = 'Cupos Reservados: ';
  tipoVehiculos: tipoVehiculo[] = []
  vehiculos: any[] | undefined; // Variable para almacenar los datos de los vehículos

  ngOnInit(){
    this.vehiculoService.getAllTipoVehiculos().subscribe((tipoVehiculos: tipoVehiculo[]) => {
      this.tipoVehiculos = tipoVehiculos
      console.log(this.tipoVehiculos)
    });
  }
  accion1():void{

  }
  accion2():void{

  }
  accion3():void{

  }
  ingresarPlaca():void{

  }

}
