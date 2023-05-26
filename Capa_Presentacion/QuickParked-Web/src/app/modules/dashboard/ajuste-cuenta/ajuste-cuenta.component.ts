import { Component } from '@angular/core';
import { DashboardModule } from '../dashboard.module';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'qp-ajuste-cuenta',
  templateUrl: './ajuste-cuenta.component.html',
  styleUrls: ['./ajuste-cuenta.component.scss']
})
export class AjusteCuentaComponent {
  constructor(private dialogRef: MatDialogRef<AjusteCuentaComponent>) { }

  nombre: string = '';
  apellido: string = '';
  telefono: string = '';
  imagenUrl: string= '';


  actualizar():void{
    
  }
  goBack() {
    this.dialogRef.close();
  }
}
