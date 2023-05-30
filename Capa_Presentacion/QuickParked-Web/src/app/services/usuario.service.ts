import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../shared/model/usuario';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient){}
  updateUsuario(id:number,usuario:Usuario):Observable<any>{
    return this.http.put<any>(`${environment.backendAPI}/api/usuarios/${id}`,usuario)
  }
}
