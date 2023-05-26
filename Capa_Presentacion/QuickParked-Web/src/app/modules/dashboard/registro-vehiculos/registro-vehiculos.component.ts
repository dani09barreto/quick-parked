import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AjusteCuentaComponent } from '../ajuste-cuenta/ajuste-cuenta.component';
import { DashboardModule } from '../dashboard.module';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { VehiculosService } from 'src/app/services/vehiculos.service';
import { tipoVehiculo } from 'src/app/shared/model/tipo.vehiculo';
import { AuthService } from 'src/app/services/auth.service';
import { CobrarComponent } from '../cobrar/cobrar.component';
import { Vehiculo } from 'src/app/shared/model/vehiculo';
import { isEmpty } from 'rxjs';

@Component({
  selector: 'qp-registro-vehiculos',
  templateUrl: './registro-vehiculos.component.html',
  styleUrls: ['./registro-vehiculos.component.scss'],
})
export class RegistroVehiculosComponent {
  constructor(
    private router: Router,
    private vehiculoService: VehiculosService,
    public dialog: MatDialog,
    private authService: AuthService
  ) {}

  placa: string = '';
  cuposDisponibles: string = 'Cupos Disponibles: 10D';
  cuposReservados: string = 'Cupos Reservados: 10D';
  placaTouched: boolean = false;
  mostrarComponenteHijo: boolean = false;
  tipoVehiculos: tipoVehiculo[] = [];
  isMotoSelected: boolean = true;
  tipoVehiculoSeleccionado: string = 'Tipo de vehiculo ⬇️';
  isDropdownOpen: boolean = false;
  opciones: string[] = [];
  vehiculos: Vehiculo[] = []// Variable para almacenar los datos de los vehículos
  regex: string = '^[A-Za-z]{3}\\d{3}$'; // Expresión regular por defecto para 'Carro'

  ngOnInit() {
    this.vehiculoService
      .getAllTipoVehiculos()
      .subscribe((tipoVehiculos: tipoVehiculo[]) => {
        console.log(tipoVehiculos)
        this.tipoVehiculos = tipoVehiculos;
        this.opciones = tipoVehiculos.map((opcion) => opcion.tipo);
      });
    this.vehiculoService.getAllVehiculos().subscribe((vehiculosRegistrados : Vehiculo[])=>{
      this.vehiculos = vehiculosRegistrados
    })
  }
  accion(opcion: string) {
    console.log('Opción seleccionada:', opcion);
  }
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
  ingresarVehiculo(): void {
    if (
      this.placa.trim().length === 0 ||
      this.tipoVehiculoSeleccionado === 'Tipo de vehiculo ⬇️'
    ) {
      alert('NO PUEDEN HABER CAMPOS VACIOS!');
      location.reload();
      return;
    }
    if (!this.placaValida()) {
      alert('Tiene que ser una placa valida!');
      location.reload();
      return;
    }

    const vehiculo: Vehiculo = new Vehiculo();
    vehiculo.placa = this.placa;
    const tipoVehiculoSeleccionadoNum = this.obtenerIdTipo(this.tipoVehiculos,this.tipoVehiculoSeleccionado)
    vehiculo.tipoVehiculo = tipoVehiculoSeleccionadoNum;
    const currentUserString = localStorage.getItem('currentUser'); // Obtiene el valor del Local Storage
    if (currentUserString) {
      const currentUser = JSON.parse(currentUserString); // Convierte la cadena en un objeto JavaScript
      const usuarioId = currentUser.usuarioId; // Obtiene el valor de usuarioId
      vehiculo.usuario = usuarioId;
    } else {
      alert('Hubo un error obteniendo el id del usuario');
      location.reload();
      return;
    }
   
    this.vehiculoService.createVehiculo(vehiculo).subscribe(
      (createdId: number) => {
        console.log('Vehículo creado con ID:', createdId);
        // Realiza cualquier acción adicional después de crear el vehículo
      },
      (error) => {
        console.error('Error al crear el vehículo:', error);
        // Maneja el error de acuerdo a tus necesidades
      }
    );
    location.reload();
  }
  obtenerIdTipo(
    tipos: tipoVehiculo[],
    tipo: string
  ): number {
    const tipoEncontrado = tipos.find((obj) => obj.tipo === tipo);
    return tipoEncontrado!!.id;
  }
  generarCobro(): void {}
  openDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    // Personaliza la posición del diálogo
    dialogConfig.position = {
      left: '50px',
      top: '50px',
    };

    // Personaliza el estilo del diálogo
    dialogConfig.panelClass = 'custom-dialog-container';

    this.dialog.open(AjusteCuentaComponent, dialogConfig);
  }
  openDialog2() {
    const dialogConfig = new MatDialogConfig();
  
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
  
    // Personaliza la posición del diálogo
    dialogConfig.position = {
      left: '1000px',
      top: '80px'
    };
  
    // Personaliza el estilo del diálogo
    dialogConfig.panelClass = 'custom-dialog-container';
  
    this.dialog.open(CobrarComponent, dialogConfig);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
