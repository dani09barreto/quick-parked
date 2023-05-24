import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { RecuperarContraseniaComponent } from './recuperar-contrasenia/recuperar-contrasenia.component';



@NgModule({
  declarations: [
    LoginComponent,
    RecuperarContraseniaComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class AutenticacionModule { }
