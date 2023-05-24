import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RespuestaIniciarSesion } from '../shared/model/auth/respuesta.iniciar.sesion';
import { BehaviorSubject, map } from 'rxjs';

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
    return true;
  }
  login(username: string, password: string) {
    const credentials = { username, password };
    const url = `${environment.backendAPI}/AGREGARRUTA`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    const options = { headers: headers };
    return this.http.post<any>(url, credentials, options).pipe(
      map((response) => {
        // extract the token and user from the response
        const token = response.token;
        const user = response.user;
        localStorage.setItem('token', token);
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.authenticationResponseSubject.next(user);
      })
    );
  }
}
