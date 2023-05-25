import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatTableDataSource } from '@angular/material/table';
import { AjusteCuentaComponent } from './ajuste-cuenta/ajuste-cuenta.component';
import { CobrarComponent } from './cobrar/cobrar.component';
import { RegistroVehiculosComponent } from './registro-vehiculos/registro-vehiculos.component';



@NgModule({
  declarations: [
    AjusteCuentaComponent,
    CobrarComponent,
    RegistroVehiculosComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatOptionModule,
    MatSelectModule,
    MatButtonModule,
    MatMenuModule,
    MatTableModule,
    MatIconModule,
    BrowserAnimationsModule,
    NgbModule
  ]
})
export class DashboardModule { }
