import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tipoVehiculo } from '../shared/model/tipo.vehiculo';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class VehiculosService {

  constructor(private http: HttpClient){}
  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  getAllTipoVehiculos():Observable<tipoVehiculo[]>{
    
    return this.http.get<tipoVehiculo[]>(`${environment.backendAPI}/api/tipoVehiculos`)
  }
}
