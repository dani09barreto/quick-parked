import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { RespuestaIniciarSesion } from 'src/app/shared/model/auth/respuesta.iniciar.sesion';

@Component({
  selector: 'qp-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router)  {}
  usuario : string = '';
  contrasenia : string = '';
  ngOnInit(): void {
    this.login();
  }

  login(): void {
    this.authService.login(this.usuario, this.contrasenia).subscribe(
      () => {
        
      },
      (error) => {
        // Lógica para manejar el error de inicio de sesión
        // Mostrar mensaje de error, restablecer campos del formulario, etc.
      }
    );
  }
}