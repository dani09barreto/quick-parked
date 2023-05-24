import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AjusteCuentaComponent } from './ajuste-cuenta/ajuste-cuenta.component';
import { CobrarComponent } from './cobrar/cobrar.component';
import { FormsModule } from '@angular/forms';
import { RegistroVehiculosComponent } from './registro-vehiculos/registro-vehiculos.component';



@NgModule({
  declarations: [
    AjusteCuentaComponent,
    CobrarComponent,
    RegistroVehiculosComponent
  ],
  imports: [
    FormsModule,
    CommonModule
  ]
})
export class DashboardModule { }
