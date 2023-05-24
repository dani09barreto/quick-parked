import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RespuestaIniciarSesion } from '../shared/model/auth/respuesta.iniciar.sesion';
import { BehaviorSubject, map } from 'rxjs';
import { IniciarSesion } from '../shared/model/auth/iniciar.sesion';
import { Usuario } from '../shared/model/usuario';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authenticationResponseSubject: BehaviorSubject<RespuestaIniciarSesion>;

  constructor(private http: HttpClient) {
    let user = localStorage.getItem('currentUser');
    if (user === null) {
      this.authenticationResponseSubject =
        new BehaviorSubject<RespuestaIniciarSesion>(
          new RespuestaIniciarSesion()
        );
    } else {
      this.authenticationResponseSubject =
        new BehaviorSubject<RespuestaIniciarSesion>(JSON.parse(user));
    }
  }
  isLoggedIn() {
    return false;
  }
  login(username: string, password: string) {
    const url = `${environment.backendAPI}/api/usuarios/IniciarSesion`;
  
    const objIniciarSesion: IniciarSesion = new IniciarSesion();
    objIniciarSesion.username = username;
    objIniciarSesion.password = password;
    return this.http.post<RespuestaIniciarSesion>(url, objIniciarSesion).pipe(
      map((response) => {
        // extract the token and user from the response
        const token = response.token;

        const user: Usuario = new Usuario();
        user.nombre = response.nombre;
        user.apellido = response.apellido;
        user.usuarioId = response.usuarioId;
        user.rolId = response.rolId;
        user.rolNombre = response.rolNombre;
        user.username = response.username;

        localStorage.setItem('token', token);
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.authenticationResponseSubject.next(response);
      })
    );
  }
}
