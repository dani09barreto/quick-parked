import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { tipoVehiculo } from '../shared/model/tipo.vehiculo';
import { environment } from 'src/environments/environment';
import { Vehiculo } from '../shared/model/vehiculo';
import { IngresarVehiculo } from '../shared/model/ingreso.vehiculo';
import { RespuestaCobro } from '../shared/model/respuesta.cobro';
@Injectable({
  providedIn: 'root'
})
export class VehiculosService {
  
  constructor(private http: HttpClient){}
  private headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  getAllTipoVehiculos():Observable<tipoVehiculo[]>{
    
    return this.http.get<tipoVehiculo[]>(`${environment.backendAPI}/api/tipoVehiculos`)
  }
  createVehiculo(vehiculo: Vehiculo): Observable<number> {
    return this.http.post<number>(`${environment.backendAPI}/api/vehiculos`, vehiculo, { headers: this.headers })
      .pipe(
        catchError((error: any) => {
          console.error('Error al crear el vehículo:', error);
          throw error;
        })
      );
  }
  getAllVehiculos():Observable<Vehiculo[]>{
    return this.http.get<Vehiculo[]>(`${environment.backendAPI}/api/vehiculos`)
  }
  crearVehiculo(vehiculo:Vehiculo){
    return this.http.post<any>(`${environment.backendAPI}/api/vehiculos`,vehiculo)
  }
  registrarVehiculo(ingresarVehiculo:IngresarVehiculo):Observable<any>{
    return this.http.post<any>(`${environment.backendAPI}/api/registroParqueaderos/ingresarVehiculo`,ingresarVehiculo)
  }
  
  
}
