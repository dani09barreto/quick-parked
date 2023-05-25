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
  displayedColumns: string[] = ['column1', 'column2', 'column3', 'column4', 'column5', 'column6'];
  dataSource = [
    { column1: 'Dato 1', column2: 'Dato 2', column3: 'Dato 3', column4: 'Dato 4', column5: 'Dato 5', column6: 'Dato 6' },
    { column1: 'Dato 1', column2: 'Dato 2', column3: 'Dato 3', column4: 'Dato 4', column5: 'Dato 5', column6: 'Dato 6' }

    // Agrega más objetos de datos según sea necesario
  ];
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
