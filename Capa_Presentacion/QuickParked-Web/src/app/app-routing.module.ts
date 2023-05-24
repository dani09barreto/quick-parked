import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistroVehiculosComponent } from './modules/dashboard/registro-vehiculos/registro-vehiculos.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AjusteCuentaComponent } from './modules/dashboard/ajuste-cuenta/ajuste-cuenta.component';
import { CobrarComponent } from './modules/dashboard/cobrar/cobrar.component';
import { LoginComponent } from './modules/autenticacion/login/login.component';
import { RecuperarContraseniaComponent } from './modules/autenticacion/recuperar-contrasenia/recuperar-contrasenia.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  
  {path:'registro-vehiculos',component:RegistroVehiculosComponent,canActivate:[AuthGuard]},
  {path:'cuenta',component:AjusteCuentaComponent,canActivate:[AuthGuard]},
  {path:'cobrar',component:CobrarComponent,canActivate:[AuthGuard]},
  {path: 'login', component:LoginComponent,pathMatch:'full'},
  {path: 'recuperarContrasenia' ,component: RecuperarContraseniaComponent, pathMatch:'full'},
  {path:'**', component:NotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
