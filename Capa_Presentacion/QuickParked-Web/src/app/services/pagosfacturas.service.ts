import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RespuestaCobro } from '../shared/model/respuesta.cobro';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PagosfacturasService {

  constructor(private http: HttpClient){}
  obtenerFactura(placa:string):Observable<RespuestaCobro>{
    return this.http.get<RespuestaCobro>(`${environment.backendAPI}/api/ventas/cobrar/${placa}`)
  }
  realizarPago(placa:string,monto:number){
    return this.http.get<string>(`${environment.backendAPI}/api/ventas/confirmarVenta/${placa}/${monto}`);
  }
}
