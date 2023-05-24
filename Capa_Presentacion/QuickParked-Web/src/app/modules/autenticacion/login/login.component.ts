import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { RespuestaIniciarSesion } from 'src/app/shared/model/auth/respuesta.iniciar.sesion';
import { AutenticacionModule } from '../autenticacion.module';
@Component({
  selector: 'qp-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) {}

  username: string = '';
  password: string = '';

  ngOnInit(): void {
    this.iniciarSesion();
  }

  iniciarSesion(): void {
    this.authService.login(this.username, this.password).subscribe(
      () => {
        // Lógica después de iniciar sesión exitosamente
      },
      (error) => {
        // Lógica para manejar el error de inicio de sesión
        // Mostrar mensaje de error, restablecer campos del formulario, etc.
      }
    );
  }
}
