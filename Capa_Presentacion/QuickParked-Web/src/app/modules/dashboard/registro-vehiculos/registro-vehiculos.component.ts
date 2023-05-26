import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AjusteCuentaComponent } from '../ajuste-cuenta/ajuste-cuenta.component';
import { DashboardModule } from '../dashboard.module';
import { MatDialog, MatDialogConfig } from'@angular/material/dialog';
import { VehiculosService } from 'src/app/services/vehiculos.service';
import { tipoVehiculo } from 'src/app/shared/model/tipo.vehiculo';

@Component({
  selector: 'qp-registro-vehiculos',
  templateUrl: './registro-vehiculos.component.html',
  styleUrls: ['./registro-vehiculos.component.scss']
})
export class RegistroVehiculosComponent {
  constructor(private router: Router, private vehiculoService:VehiculosService,public dialog: MatDialog) {}
  placa: string = '';
  cuposDisponibles: string = 'Cupos Disponibles: ';
  cuposReservados: string = 'Cupos Reservados: ';
  placaTouched: boolean = false;
  mostrarComponenteHijo: boolean= false;
  tipoVehiculos: tipoVehiculo[] = []
  materials: string[] = ['Material 1', 'Material 2', 'Material 3'];
  tipoVehiculoSeleccionado: string = 'Tipo de vehiculo';
  isDropdownOpen: boolean = false;
  opciones: string[] = []
  vehiculos: any[] | undefined; // Variable para almacenar los datos de los vehículos
  regex: string = '^[A-Za-z]{3}\\d{3}$'; // Expresión regular por defecto para 'Carro'

  ngOnInit(){
    this.vehiculoService.getAllTipoVehiculos().subscribe((tipoVehiculos: tipoVehiculo[]) => {
      this.tipoVehiculos = tipoVehiculos
      this.opciones = tipoVehiculos.map(opcion => opcion.tipo);
    });
  }
  accion(opcion: string){
    console.log('Opción seleccionada:', opcion);
  }
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
  
  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  selectTipoVehiculo(material: string) {
    this.tipoVehiculoSeleccionado = material;
    this.isDropdownOpen = false;
  }
  convertToUppercase() {
    this.placa = this.placa.toUpperCase();
  }
  ingresarPlaca():void{

  }
  generarCobro():void{

  }
  openDialog() {
    const dialogConfig = new MatDialogConfig();
  
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
  
    // Personaliza la posición del diálogo
    dialogConfig.position = {
      left: '50px',
      top: '50px'
    };
  
    // Personaliza el estilo del diálogo
    dialogConfig.panelClass = 'custom-dialog-container';
  
    this.dialog.open(AjusteCuentaComponent, dialogConfig);
  }
  

}
