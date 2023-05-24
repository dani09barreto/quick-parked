import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegistroVehiculosComponent } from './modules/dashboard/registro-vehiculos/registro-vehiculos.component';
@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    RegistroVehiculosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
